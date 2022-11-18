package com.lyx.attendance.service;

import com.lyx.attendance.entity.Leave;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.attendance.entity.req.LeaveListPageReq;
import com.lyx.attendance.entity.req.LeaveSaveReq;
import com.lyx.attendance.entity.vo.LeaveVO;
import com.lyx.common.base.entity.PageUtils;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
public interface ILeaveService extends IService<Leave> {

     /**
       * 分页查询请假信息
       */
     PageUtils<LeaveVO> listPage(LeaveListPageReq req);

     /**
       * 请假申请
       */
    void appLeave(LeaveSaveReq req);

    /**
     * 删除请假信息,同时删除对应流程
     * @param id
     * @return void
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    void removeLeaveAndProcessByInstanceId(Long id,String instantId);
}
