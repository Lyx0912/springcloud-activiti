package com.lyx.attendance.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyx.attendance.clients.ActivitiBusinessTripFeignService;
import com.lyx.attendance.clients.ActivitiProcessInstanceFeignService;
import com.lyx.attendance.entity.Leave;
import com.lyx.attendance.entity.Travel;
import com.lyx.attendance.entity.req.TravelListPageReq;
import com.lyx.attendance.entity.req.TravelSaveReq;
import com.lyx.attendance.entity.vo.LeaveVO;
import com.lyx.attendance.entity.vo.TravelVO;
import com.lyx.attendance.mapper.TravelMapper;
import com.lyx.attendance.service.ITravelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.entity.dto.NodeDTO;
import com.lyx.common.base.result.R;
import com.lyx.common.web.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
@Service
@RequiredArgsConstructor
public class TravelServiceImpl extends ServiceImpl<TravelMapper, Travel> implements ITravelService {

    private final ActivitiBusinessTripFeignService businessTripFeignService;
    private final ActivitiProcessInstanceFeignService processInstanceFeignService;


    /**
     * 分页查询出差信息表
     *
     * @return com.lyx.common.base.entity.PageUtils<com.lyx.attendance.entity.vo.TravelVO>
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    @Override
    public PageUtils<TravelVO> listPage(TravelListPageReq req) {
        // 构建分页对象
        Page<Travel> page = new Page<>(req.getPageNo(),req.getPageSize());
        LambdaQueryWrapper<Travel> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByAsc(Travel::getId);
        // 如果是非管理员就查询自己的信息
        if(req.getManager() == 0) {
            wrapper.eq(Travel::getUserId,UserContext.getCurrentUserId());
        }else {
            if(StringUtils.isNotEmpty(req.getUname())){
                wrapper.like(Travel::getUname,req.getUname());
            }
        }
        if(req.getResult()!= null) {
            wrapper.eq(Travel::getResult,req.getResult());
        }
        if (req.getStartTime()!=null){
            wrapper.ge(Travel::getStartTime, req.getStartTime());
        }
        if (req.getEndTime()!=null){
            wrapper.le(Travel::getEndTime,req.getEndTime());
        }
        // 执行查询操作 -》 返回结果封装在page对象
        baseMapper.selectPage(page,wrapper);
        PageUtils<TravelVO> pageUtils = new PageUtils<>();
        List<TravelVO> vos = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        // 遍历结果封装成vo
        if(CollectionUtil.isNotEmpty(page.getRecords())){
            page.getRecords().stream().forEach(item->{
                TravelVO vo = new TravelVO();
                BeanUtils.copyProperties(item,vo);
                ids.add(vo.getId());
                // 放入vos
                vos.add(vo);
            });
        }
        // 根据编号获取流程节点信息
        if(CollectionUtil.isNotEmpty(ids)){
            R<List<NodeDTO>> result = businessTripFeignService.travelNodeInfo(ids);
            List<NodeDTO> data = result.getData();
            for (int i = 0; i < result.getData().size(); i++) {
                NodeDTO dto = data.get(i);
                TravelVO vo = vos.get(i);
                BeanUtils.copyProperties(dto,vo);
            }
        }
        pageUtils.setList(vos);
        pageUtils.setTotal(page.getTotal());
        return pageUtils;
    }

    /**
     * 新增出差信息
     *
     * @param req
     * @return void
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    @Override
    public void saveTravel(TravelSaveReq req) {
        Travel travel = new Travel();
        BeanUtils.copyProperties(req,travel);
        travel.setUserId(UserContext.getCurrentUserId());
        // 计算请假时间
        Duration duration = Duration.between(req.getStartTime(),req.getEndTime());
        // 计算请假天数
        travel.setTravelDays((int) duration.toDays());
        save(travel);
        // 调用activiti开启出差流程并获取实例id
        Map<String,String> params = new HashMap<>();
        params.put("travelId",travel.getId().toString());
        params.put("emp",req.getUname());
        R r = businessTripFeignService.startProcess(params);
        // 流程实例编号
        travel.setProcessInstance((String) r.getData());
        // 设为待审批状态
        travel.setResult(0);
        updateById(travel);
    }

    @Override
    public void removeTravelAndProcessByInstanceId(Long id, String instantId) {
        removeById(id);
        // 远程调用acviti服务删除对应流程
        if(StringUtils.isNotEmpty(instantId)) {
            processInstanceFeignService.remove(instantId);
        }
    }
}
