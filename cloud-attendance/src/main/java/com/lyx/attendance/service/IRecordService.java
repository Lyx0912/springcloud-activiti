package com.lyx.attendance.service;

import com.lyx.attendance.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.attendance.entity.req.RecordSelectPageReq;
import com.lyx.common.base.entity.PageUtils;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
public interface IRecordService extends IService<Record> {

    /**
     * 分页查询出勤记录
     * @param req
     * @return com.lyx.common.base.entity.PageUtils<com.lyx.attendance.entity.Record>
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    PageUtils<Record> listPage(RecordSelectPageReq req);
}
