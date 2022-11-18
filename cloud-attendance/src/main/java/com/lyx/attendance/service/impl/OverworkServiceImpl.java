package com.lyx.attendance.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyx.attendance.entity.Leave;
import com.lyx.attendance.entity.Overwork;
import com.lyx.attendance.entity.req.OverworkExcelReq;
import com.lyx.attendance.entity.req.OverworkSelectReq;
import com.lyx.attendance.entity.vo.LeaveVO;
import com.lyx.attendance.mapper.OverworkMapper;
import com.lyx.attendance.service.IOverworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.entity.dto.NodeDTO;
import com.lyx.common.base.exception.BizException;
import com.lyx.common.base.result.R;
import com.lyx.common.base.result.ResultCode;
import com.lyx.common.web.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
public class OverworkServiceImpl extends ServiceImpl<OverworkMapper, Overwork> implements IOverworkService {

    /**
     * 分页查询出勤记录
     *
     * @param req
     * @return com.lyx.common.base.entity.PageUtils<com.lyx.attendance.entity.Overwork>
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    @Override
    public PageUtils<Overwork> listPage(OverworkSelectReq req) {
        // 构建分页对象
        Page<Overwork> page = new Page<>(req.getPageNo(),req.getPageSize());
        LambdaQueryWrapper<Overwork> wrapper = Wrappers.lambdaQuery();
        if(StringUtils.isNotEmpty(req.getUserName())){
            wrapper.like(Overwork::getUserName,req.getUserName());
        }
        // 执行查询操作 -》 返回结果封装在page对象
        baseMapper.selectPage(page,wrapper);
        PageUtils<Overwork> pageUtils = new PageUtils<>();
        pageUtils.setList(page.getRecords());
        pageUtils.setPageNo(page.getCurrent());
        pageUtils.setTotal(page.getTotal());
        // 返回分页对象
        return pageUtils;
    }

    /**
     * 批量新增
     *
     * @param file
     * @return void
     * @author 黎勇炫
     * @create 2022/11/12
     * @email 1677685900@qq.com
     */
    @Override
    public void saveBatchByExcel(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), OverworkExcelReq.class, new OwAnalysisEventListener(this)).sheet().doRead();
        } catch (IOException e) {
            throw new BizException(ResultCode.SYSTEM_EXECUTION_ERROR);
        }
    }

    @RequiredArgsConstructor
    class OwAnalysisEventListener extends AnalysisEventListener<OverworkExcelReq> {

        private final IOverworkService overworkService;

        private List<OverworkExcelReq> data= new ArrayList<>();

        @Override
        public void invoke(OverworkExcelReq overwork, AnalysisContext analysisContext) {
            // 逐行处理
            data.add(overwork);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            // 读取完成后批量添加
            List<Overwork> owInfo = data.stream().map(item -> {
                Overwork overwork = new Overwork();
                BeanUtils.copyProperties(item, overwork);
                // 设置开始时间和结束时间
                overwork.setStartTime(LocalDateTime.parse(item.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                overwork.setEndTime(LocalDateTime.parse(item.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                // 计算加班时长
                Duration duration = Duration.between(overwork.getStartTime(), overwork.getEndTime());
                overwork.setDuration(String.valueOf(duration.toHours()));
                return overwork;
            }).collect(Collectors.toList());
            // 批量新增
            overworkService.saveBatch(owInfo);
        }

    }
}
