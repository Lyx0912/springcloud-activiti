package com.lyx.admin.ser.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.admin.dto.UserAuthDTO;
import com.lyx.admin.ser.config.AdminMapStruct;
import com.lyx.admin.ser.entity.*;
import com.lyx.admin.ser.entity.req.SaveUserReq;
import com.lyx.admin.ser.entity.req.UserListPageReq;
import com.lyx.admin.ser.entity.vo.SysMenuSelectVO;
import com.lyx.admin.ser.entity.vo.SysUserVO;
import com.lyx.admin.ser.mapper.SysUserMapper;
import com.lyx.admin.ser.service.*;
import com.lyx.common.base.constant.GlobalConstants;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.result.ResultCode;
import com.lyx.common.base.utils.AssertUtil;
import com.lyx.common.web.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author： 黎勇炫
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    private final ISysUserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapStruct adminMapStruct;
    private final ISysRoleMenuService sysRoleMenuService;
    private final ISysRolePermissionService rolePermissionService;
    private final ISysPermissionService permissionService;

    public UserAuthDTO getByUsername(String username) {
        UserAuthDTO userAuthInfo = this.baseMapper.getByUsername(username);
        return userAuthInfo;
    }

    @Override
    public void createUser(SaveUserReq req) {
        // 生成密码
        String passwd = passwordEncoder.encode(GlobalConstants.USER_DEFAULT_PASSWORD);
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(req, sysUser);
        sysUser.setPassword(passwd);
        save(sysUser);
        // 维护角色关系
        saveUserRoles(req.getRoleIds(), sysUser.getId());
    }

    /**
     * 获取用户详情信息
     *
     * @param userId
     */
    @Override
    public SysUserVO userDetail(Long userId) {
        SysUser user = lambdaQuery().eq(SysUser::getId, userId).one();
        // 判断用户是否为空
        Assert.notNull(user, ResultCode.USER_NOT_EXIST.getMsg());
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(user,vo);
        // 查询用户绑定的角色id
        vo.setRoleIds(userRoleService.selectRoleIds(userId));
        // 查询角色绑定的菜单
        List<SysRoleMenu> roleMenus = sysRoleMenuService.lambdaQuery().in(SysRoleMenu::getRoleId, vo.getRoleIds()).list();
        vo.setMenuIds(roleMenus.stream().map(item->item.getMenuId()).collect(Collectors.toList()));
        // 查询角色绑定的权限
        List<SysRolePermission> rolePermissions = rolePermissionService.lambdaQuery().in(SysRolePermission::getRoleId, vo.getRoleIds()).list();
        List<Long> pid = rolePermissions.stream().map(item->item.getPermissionId()).collect(Collectors.toList());
        // 根据权限id获取权限按钮名称
        List<SysPermission> permissions = permissionService.lambdaQuery().in(SysPermission::getId, pid).list();
        vo.setPermissions(new ArrayList<>(permissions.stream().map(item->item.getBtnSign()).collect(Collectors.toSet())));
        return vo;
    }

    /**
     * 更新用户信息
     */
    @Override
    public void updateUserInfo(SaveUserReq req, Long userId) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(req,user);
        lambdaUpdate().eq(SysUser::getId,userId).update(user);
        // 维护角色列表
        userRoleService.deleteByUserId(userId);
        saveUserRoles(req.getRoleIds(), userId);
    }

    /**
     * 分页查询用户信息
     *
     * @param req
     */
    @Override
    public PageUtils<SysUserVO> pageUser(UserListPageReq req) {
        // 构建分页对象
        Page<SysUser> page = new Page<>(req.getPageNo(),req.getPageSize());
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery();
        if(!StringUtils.isEmpty(req.getKeyword())){
            wrapper.like(SysUser::getUsername,req.getKeyword()).or().like(SysUser::getNickname,req.getKeyword());
        }
        baseMapper.selectPage(page,wrapper);
        PageUtils<SysUserVO> pageUtils = new PageUtils<>();
        pageUtils.setList(adminMapStruct.sysUser2SysUserVO(page.getRecords()));
        pageUtils.setPageNo(page.getCurrent());
        pageUtils.setTotal(page.getTotal());
        // 返回分页对象
        return pageUtils;
    }

    /**
     * 获取当前用户信息
     */
    @Override
    public SysUserVO currentUserInfo() {
        Long userId  = UserContext.getCurrentUserId();
        AssertUtil.notEmpty(userId,ResultCode.TOKEN_INVALID_OR_EXPIRED);
        SysUserVO vo = userDetail(userId);
        AssertUtil.notEmpty(userId,ResultCode.USER_NOT_EXIST);
        return vo;
    }

    private void saveUserRoles(List<Long> roleIds, Long userId) {
        if (CollectionUtil.isNotEmpty(roleIds)) {
            List<SysUserRole> sysUserRoles = new ArrayList<>();
            roleIds.forEach(roleId -> {
                sysUserRoles.add(new SysUserRole(userId, roleId));
            });
            userRoleService.saveBatch(sysUserRoles);
        }
    }


}
