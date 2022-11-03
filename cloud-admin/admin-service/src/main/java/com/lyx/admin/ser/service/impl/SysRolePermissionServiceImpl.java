package com.lyx.admin.ser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.admin.ser.entity.SysPermission;
import com.lyx.admin.ser.entity.SysRolePermission;
import com.lyx.admin.ser.mapper.SysPermissionMapper;
import com.lyx.admin.ser.mapper.SysRolePermissionMapper;
import com.lyx.admin.ser.service.ISysRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * @author 黎勇炫
 * @date 2022年10月31日 22:35
 */
@Service
public class SysRolePermissionServiceImpl  extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {
}
