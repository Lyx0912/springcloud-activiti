package com.lyx.attendance.service;

import com.lyx.attendance.entity.Overwork;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.attendance.entity.req.OverworkSelectReq;
import com.lyx.common.base.entity.PageUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
public interface IOverworkService extends IService<Overwork> {

    /**
     * 分页查询出勤记录
     * @param req
     * @return com.lyx.common.base.entity.PageUtils<com.lyx.attendance.entity.Overwork>
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    PageUtils<Overwork> listPage(OverworkSelectReq req);

    /**
     * 批量新增
     * @param file
     * @return void
     * @author 黎勇炫
     * @create 2022/11/12
     * @email 1677685900@qq.com
     */
    void saveBatchByExcel(MultipartFile file);
}
