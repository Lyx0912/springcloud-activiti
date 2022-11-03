package com.lyx.admin.ser.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.admin.ser.config.AdminMapStruct;
import com.lyx.admin.ser.entity.SysRole;
import com.lyx.admin.ser.entity.SysRoleMenu;
import com.lyx.admin.ser.entity.SysRolePermission;
import com.lyx.admin.ser.entity.req.RoleListPageReq;
import com.lyx.admin.ser.entity.req.SaveSysRoleReq;
import com.lyx.admin.ser.entity.vo.SysRoleVO;
import com.lyx.admin.ser.mapper.SysRoleMapper;
import com.lyx.admin.ser.service.ISysRoleMenuService;
import com.lyx.admin.ser.service.ISysRolePermissionService;
import com.lyx.admin.ser.service.ISysRoleService;
import com.lyx.common.base.constant.GlobalConstants;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.result.ResultCode;
import com.lyx.common.base.utils.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author 黎勇炫
 * @date 2022年10月27日 19:11
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    private final AdminMapStruct adminMapStruct;
    private final ISysRoleMenuService roleMenuService;
    private final ISysRolePermissionService rolePermissionService;

    @Override
    public void updateStatus(Long id, int status) {
        SysRole role = new SysRole();
        role.setId(id);
        role.setStatus(status);
        updateById(role);
    }

    @Override
    public List<SysRoleVO> listRoleVO() {
        List<SysRole> sysRoles = lambdaQuery().eq(SysRole::getStatus, GlobalConstants.STATUS_ON).select(SysRole::getId, SysRole::getName).list();
        // 将vo对象列表转换成实体列表
        if(CollectionUtil.isNotEmpty(sysRoles)){
            return adminMapStruct.sysRoleToSysRoleVO(sysRoles);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 分页拆查询角色
     *
     * @param req
     */
    @Override
    public PageUtils<SysRoleVO> listPage(RoleListPageReq req) {
        // 构建分页对象
        Page<SysRole> page = new Page<>(req.getPageNo(),req.getPageSize());
        LambdaQueryWrapper<SysRole> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByAsc(SysRole::getSort);
        if(!StringUtils.isEmpty(req.getKeyword())){
            wrapper.like(SysRole::getName,req.getKeyword());
        }
        baseMapper.selectPage(page,wrapper);
        PageUtils<SysRoleVO> pageUtils = new PageUtils<>();
        pageUtils.setList(adminMapStruct.sysRoleToSysRoleVO(page.getRecords()));
        pageUtils.setPageNo(page.getCurrent());
        pageUtils.setTotal(page.getTotal());
        // 返回分页对象
        return pageUtils;
    }

    /**
     * 新增角色
     *
     * @param req
     */
    @Override
    public void createRole(SaveSysRoleReq req) {
        SysRole role = new SysRole();
        BeanUtils.copyProperties(req,role);
        save(role);
    }

    /**
     * 更新角色信息
     *
     * @param req
     */
    @Override
    public void updateRole(SaveSysRoleReq req) {
        AssertUtil.notEmpty(req.getId(),ResultCode.PARAM_VALID_FAIL);
        SysRole role = new SysRole();
        BeanUtils.copyProperties(req,role);
        // 更新
        updateById(role);
    }

    /**
     * 删除角色
     *
     * @param ids
     */
    @Override
    public void removeRole(List<Long> ids) {
        removeByIds(ids);
        // 删除角色关联的菜单
        roleMenuService.lambdaUpdate().in(SysRoleMenu::getRoleId,ids).remove();
        // 删除角色关联的权限
        rolePermissionService.lambdaUpdate().in(SysRolePermission::getRoleId,ids).remove();
    }
}
