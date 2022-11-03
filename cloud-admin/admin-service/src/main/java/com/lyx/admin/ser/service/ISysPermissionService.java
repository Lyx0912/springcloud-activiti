package com.lyx.admin.ser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.admin.ser.entity.SysPermission;
import com.lyx.admin.ser.entity.SysRolePermission;
import com.lyx.admin.ser.entity.req.CommonReq;
import com.lyx.admin.ser.entity.req.SavePermissionReq;
import com.lyx.admin.ser.entity.vo.SysPermissionVO;
import com.lyx.admin.ser.entity.vo.SysServiceVO;

import java.util.List;

/**
 * @author 黎勇炫
 * @date 2022年10月10日 17:21
 */
public interface ISysPermissionService extends IService<SysPermission> {

    boolean refreshPermRolesRules();

     /**
       * 根据菜单查询权限
       */
    List<SysPermissionVO> listByMenuId(Long menuId);

     /**
       * 新增权限
       */
    void createPermission(SavePermissionReq req);

     /**
       * 更新权限
       */
    void updatePermission(SavePermissionReq req);

     /**
       * 获取服务列表
       */
     List<SysServiceVO> getServiceList();

     /**
       * 删除权限
       */
    void deletePermission(List<Long> ids);

     /**
       * 更新角色绑定的权限
       */
    void updateRoleBingdingInfo(Long roleId, CommonReq req);

     /**
       * 查询角色绑定的权限
       */
    List<Long> listRolePermission(Long roleId);
}
