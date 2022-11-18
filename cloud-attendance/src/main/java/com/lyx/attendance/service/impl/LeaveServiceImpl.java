package com.lyx.attendance.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyx.attendance.clients.ActivitiLeaveFeignService;
import com.lyx.attendance.clients.ActivitiProcessInstanceFeignService;
import com.lyx.attendance.entity.Leave;
import com.lyx.attendance.entity.Travel;
import com.lyx.attendance.entity.req.LeaveListPageReq;
import com.lyx.attendance.entity.req.LeaveSaveReq;
import com.lyx.attendance.entity.vo.LeaveVO;
import com.lyx.attendance.mapper.LeaveMapper;
import com.lyx.attendance.service.ILeaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.entity.dto.NodeDTO;
import com.lyx.common.base.result.R;
import com.lyx.common.web.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements ILeaveService {

    private final ActivitiLeaveFeignService activitiFeignService;
    private final ActivitiProcessInstanceFeignService processInstanceFeignService;

    /**
     * 分页查询请假信息
     *
     * @param req
     */
    @Override
    public PageUtils<LeaveVO> listPage(LeaveListPageReq req) {
        // 构建分页对象
        Page<Leave> page = new Page<>(req.getPageNo(),req.getPageSize());
        LambdaQueryWrapper<Leave> wrapper = Wrappers.lambdaQuery();
        // 如果是非管理员就查询自己的信息
        if(req.getManager() == 0) {
            wrapper.eq(Leave::getUserId,UserContext.getCurrentUserId());
        }else {
            if(StringUtils.isNotEmpty(req.getUname())){
                wrapper.like(Leave::getUname,req.getUname());
            }
        }
        // 按照id排序
        wrapper.orderByAsc(Leave::getId);
        if(req.getResult()!= null) {
            wrapper.eq(Leave::getResult,req.getResult());
        }
        if(!(null == req.getType())){
            wrapper.eq(Leave::getType,req.getType());
        }
        if (req.getStartTime()!=null){
            wrapper.gt(Leave::getStartTime, req.getStartTime());
        }
        if (req.getEndTime()!=null){
            wrapper.lt(Leave::getEndTime,req.getEndTime());
        }
        // 执行查询操作 -》 返回结果封装在page对象
        baseMapper.selectPage(page,wrapper);
        PageUtils<LeaveVO> pageUtils = new PageUtils<>();
        List<Long> ids = new ArrayList<>();
        List<LeaveVO> vos = page.getRecords().stream().map(item -> {
            LeaveVO vo = new LeaveVO();
            BeanUtils.copyProperties(item, vo);
            ids.add(vo.getId());
            return vo;
        }).collect(Collectors.toList());
        // 根据编号获取流程节点信息
        if(CollectionUtil.isNotEmpty(ids)){
            R<List<NodeDTO>> result = activitiFeignService.leaveInfo(ids);
            List<NodeDTO> data = result.getData();
            for (int i = 0; i < result.getData().size(); i++) {
                NodeDTO dto = data.get(i);
                LeaveVO vo = vos.get(i);
                vo.setCurrentNode(dto.getCurrentNode());
                vo.setCurrentRole(dto.getCurrentRole());
            }
        }
        pageUtils.setList(vos);
        pageUtils.setPageNo(page.getCurrent());
        pageUtils.setTotal(page.getTotal());
        // 返回分页对象
        return pageUtils;
    }

    /**
     * 请假申请
     *
     * @param req
     */
    @Override
    public void appLeave(LeaveSaveReq req) {
        Leave leave = new Leave();
        BeanUtils.copyProperties(req,leave);
        // 计算请假时间
        Duration duration = Duration.between(req.getStartTime(),req.getEndTime());
        // 计算请假天数
        leave.setLeaveDays((int) duration.toDays());
        // 用户编号
        leave.setUserId(UserContext.getCurrentUserId());
        // 请假人
        leave.setUname(req.getUname());
        save(leave);
        // 远程调用activiti服务并开启一个请假流程实例，请假id为业务key
        Map<String,String> params = new HashMap<>();
        params.put("leaveId",leave.getId().toString());
        params.put("emp",req.getUname());
        R r = activitiFeignService.startProcess(params);
        // 流程实例编号
        leave.setResult(0);
        leave.setProcessInstance((String) r.getData());
        updateById(leave);
    }

    /**
     * 删除请假信息,同时删除对应流程
     * @param id
     * @param instantId
     * @return void
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    @Override
    public void removeLeaveAndProcessByInstanceId(Long id,String instantId) {
        removeById(id);
        // 远程调用acviti服务删除对应流程
        processInstanceFeignService.remove(instantId);
    }
}
