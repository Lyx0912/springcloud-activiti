package com.lyx.admin.ser.service;

import com.lyx.admin.ser.entity.req.SavePermissionReq;
import com.lyx.admin.ser.entity.vo.SysPermissionVO;
import com.lyx.admin.ser.entity.vo.SysServiceVO;

import java.util.List;

/**
 * @author 黎勇炫
 * @date 2022年10月10日 17:21
 */
public interface ISysPermissionService {

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
}
