package com.lyx.admin.ser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.admin.ser.entity.SysRole;
import com.lyx.admin.ser.entity.req.RoleListPageReq;
import com.lyx.admin.ser.entity.req.SaveSysRoleReq;
import com.lyx.admin.ser.entity.vo.SysRoleVO;
import com.lyx.common.base.entity.PageUtils;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * @author： 黎勇炫
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 更新角色状态
     * @param id
     * @param status
     */
    void updateStatus(Long id, int status);

     /**
       * 查询角色列表
       */
    List<SysRoleVO> listRoleVO();

     /**
      * 分页拆查询角色
      */
    PageUtils<SysRoleVO> listPage(RoleListPageReq req);

     /**
       * 新增角色
       */
    void createRole(SaveSysRoleReq req);

     /**
       * 更新角色信息
       */
    void updateRole(SaveSysRoleReq req);

     /**
       * 删除角色
       */
    void removeRole(List<Long> ids);
}
