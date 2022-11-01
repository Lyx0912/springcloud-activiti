package com.lyx.admin.ser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.admin.ser.entity.SysRole;
import com.lyx.admin.ser.entity.SysRoleMenu;
import com.lyx.admin.ser.mapper.SysRoleMapper;
import com.lyx.admin.ser.mapper.SysRoleMenuMapper;
import com.lyx.admin.ser.service.ISysRoleMenuService;
import com.lyx.admin.ser.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * @author 黎勇炫
 * @date 2022年11月01日 20:43
 */
@Service
public class SysRoleMenuServiceImpl  extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {
}
