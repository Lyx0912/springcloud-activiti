package com.lyx.attendance.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyx.attendance.entity.Overwork;
import com.lyx.attendance.entity.Record;
import com.lyx.attendance.entity.req.RecordSelectPageReq;
import com.lyx.attendance.mapper.RecordMapper;
import com.lyx.attendance.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.common.base.entity.PageUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    /**
     * 分页查询出勤记录
     *
     * @param req
     * @return com.lyx.common.base.entity.PageUtils<com.lyx.attendance.entity.Record>
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    @Override
    public PageUtils<Record> listPage(RecordSelectPageReq req) {
        // 构建分页对象
        Page<Record> page = new Page<>(req.getPageNo(),req.getPageSize());
        LambdaQueryWrapper<Record> wrapper = Wrappers.lambdaQuery();
        if(StringUtils.isNotEmpty(req.getUserName())){
            wrapper.like(Record::getUserName,req.getUserName());
        }
        // 执行查询操作 -》 返回结果封装在page对象
        baseMapper.selectPage(page,wrapper);
        PageUtils<Record> pageUtils = new PageUtils<>();
        pageUtils.setList(page.getRecords());
        pageUtils.setPageNo(page.getCurrent());
        pageUtils.setTotal(page.getTotal());
        // 返回分页对象
        return pageUtils;
    }
}
