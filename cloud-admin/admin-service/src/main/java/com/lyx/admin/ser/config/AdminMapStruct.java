package com.lyx.admin.ser.config;

import com.lyx.admin.ser.entity.SysRole;
import com.lyx.admin.ser.entity.SysUser;
import com.lyx.admin.ser.entity.vo.SysRoleSelectVO;
import com.lyx.admin.ser.entity.vo.SysRoleVO;
import com.lyx.admin.ser.entity.vo.SysUserVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author 黎勇炫
 * @date 2022年10月27日 21:47
 */
@Mapper(componentModel = "spring")
public interface AdminMapStruct {

    List<SysRoleVO> sysRoleToSysRoleVO(List<SysRole> sysRoles);

    List<SysUserVO> sysUser2SysUserVO(List<SysUser> sysUsers);
}
