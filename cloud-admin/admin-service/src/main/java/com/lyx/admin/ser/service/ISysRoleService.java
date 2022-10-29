package com.lyx.admin.ser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.admin.ser.entity.SysRole;
import com.lyx.admin.ser.entity.vo.SysRoleVO;

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
}
