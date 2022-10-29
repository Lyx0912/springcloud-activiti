package com.lyx.admin.ser.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.admin.dto.UserAuthDTO;
import com.lyx.admin.ser.entity.SysUser;
import com.lyx.admin.ser.entity.req.SaveUserReq;
import com.lyx.admin.ser.entity.req.UserListPageReq;
import com.lyx.admin.ser.entity.vo.SysUserVO;
import com.lyx.common.base.entity.PageUtils;

import java.util.List;

/**
 * @author： 黎勇炫
 */
public interface ISysUserService extends IService<SysUser> {


    /**
     * 根据用户名获取认证用户信息，携带角色和密码
     *
     * @param username
     * @return
     */
    UserAuthDTO getByUsername(String username);


    /**
     * 创建用户
     * @param req
     */
    void createUser(SaveUserReq req);

     /**
       * 获取用户详情信息
       */
    SysUserVO userDetail(Long userId);

     /**
       * 更新用户信息
       */
    void updateUserInfo(SaveUserReq req, Long userId);

     /**
       * 分页查询用户信息
       */
    PageUtils<SysUserVO> pageUser(UserListPageReq req);

     /**
       * 获取当前用户inxi
       */
    SysUserVO currentUserInfo();

//    /**
//     * 更新用户状态
//     * @param userId
//     * @param status
//     */
//    void updateStatus(Long userId, Integer status);
//
//    /**
//     * 获取当前用户信息
//     * @return
//     */
//    SysUserVO currentUserInfo();
}
