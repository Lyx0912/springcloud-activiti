package com.lyx.admin.ser.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.admin.ser.config.AdminMapStruct;
import com.lyx.admin.ser.config.ServiceConfig;
import com.lyx.admin.ser.entity.SysPermission;
import com.lyx.admin.ser.entity.SysRoleMenu;
import com.lyx.admin.ser.entity.SysRolePermission;
import com.lyx.admin.ser.entity.req.CommonReq;
import com.lyx.admin.ser.entity.req.SavePermissionReq;
import com.lyx.admin.ser.entity.vo.SysPermissionVO;
import com.lyx.admin.ser.entity.vo.SysServiceVO;
import com.lyx.admin.ser.mapper.SysPermissionMapper;
import com.lyx.admin.ser.service.ISysPermissionService;
import com.lyx.admin.ser.service.ISysRolePermissionService;
import com.lyx.common.base.constant.GlobalConstants;
import com.lyx.common.base.result.ResultCode;
import com.lyx.common.base.utils.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 黎勇炫
 * @date 2022年10月10日 17:20
 */
@Service
@RequiredArgsConstructor
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    private final RedisTemplate redisTemplate;
    private final AdminMapStruct adminMapStruct;
    private final ServiceConfig serviceConfig;
    private final ISysRolePermissionService rolePermissionService;

    @Override
    public boolean refreshPermRolesRules() {
        redisTemplate.delete(Arrays.asList(GlobalConstants.URL_PERM_ROLES_KEY));
        List<SysPermission> permissions = this.baseMapper.listPermRoles();
        if (CollectionUtil.isNotEmpty(permissions)) {
            List<SysPermission> urlPermList = permissions.stream()
                    .filter(item -> StrUtil.isNotBlank(item.getUrlPerm()))
                    .collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(urlPermList)) {
                Map<String, List<String>> urlPermRoles = new HashMap<>();
                urlPermList.stream().forEach(item -> {
                    String perm = item.getUrlPerm();
                    List<String> roles = item.getRoles();
                    urlPermRoles.put(perm, roles);
                });
                redisTemplate.opsForHash().putAll(GlobalConstants.URL_PERM_ROLES_KEY, urlPermRoles);
            }
        }
        return true;
    }

    /**
     * 根据菜单查询权限
     *
     * @param menuId
     */
    @Override
    public List<SysPermissionVO> listByMenuId(Long menuId) {
        List<SysPermission> permissioms = lambdaQuery().eq(SysPermission::getMenuId, menuId).list();
        if(CollectionUtil.isNotEmpty(permissioms)){
            List<SysPermissionVO> sysPermissionVOS = adminMapStruct.sysPermission2SysPermissionVO(permissioms);
            return sysPermissionVOS;
        }
        return null;
    }

    /**
     * 新增权限
     *
     * @param req
     */
    @Override
    public void createPermission(SavePermissionReq req) {
        // 更新缓存中的权限信息
        refreshPermRolesRules();
        SysPermission permission = new SysPermission();
        BeanUtils.copyProperties(req,permission);
        // 拼接url
        String permUrl = getPermUrl(req);
        permission.setUrlPerm(permUrl);
        save(permission);
    }

    /**
     * 更新权限
     *
     * @param req
     */
    @Override
    public void updatePermission(SavePermissionReq req) {
        // 更新缓存中的权限信息
        refreshPermRolesRules();
        AssertUtil.notEmpty(req.getId(), ResultCode.PARAM_VALID_FAIL);
        SysPermission permission = new SysPermission();
        // 拼接url
        String permUrl = getPermUrl(req);
        permission.setUrlPerm(permUrl);
        BeanUtils.copyProperties(req,permission);
        updateById(permission);
    }

    /**
     * 获取服务列表
     */
    @Override
    public List<SysServiceVO> getServiceList() {
        List<String> services = serviceConfig.getServices();
        //校验服务列表的正确性
        List<String> res = services.stream().filter(service -> {
            return service.split(",").length == 2;
        }).collect(Collectors.toList());
        // 将服务转成SysServiceVO对象列表并返回
        return res.stream().map(service->{
            SysServiceVO vo = new SysServiceVO();
            String[] arr = service.split(",");
            vo.setServiceCode(arr[0]);
            vo.setServiceName(arr[1]);
            return vo;
        }).collect(Collectors.toList());

    }

    /**
     * 删除权限
     * @param ids
     */
    @Override
    public void deletePermission(List<Long> ids) {
        // 更新缓存中的权限信息
        refreshPermRolesRules();
        // 删除权限
        removeByIds(ids);
        // 删除关联表中的权限
        rolePermissionService.lambdaUpdate().in(SysRolePermission::getPermissionId,ids).remove();
    }

    /**
     * 更新角色绑定的权限
     *
     * @param roleId
     * @param req
     */
    @Override
    public void updateRoleBingdingInfo(Long roleId, CommonReq req) {
        // 删除指定角色绑定的菜单
        rolePermissionService.lambdaUpdate().in(SysRolePermission::getRoleId,roleId).remove();
        // 插入新的绑定信息
        List<SysRolePermission> sysRoleMenus = req.getIds().stream().map(id -> {
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(id);
            return rolePermission;
        }).collect(Collectors.toList());

        if(CollectionUtil.isNotEmpty(sysRoleMenus)){
            rolePermissionService.saveBatch(sysRoleMenus);
        }
    }

    /**
     * 查询角色绑定的权限
     *
     * @param roleId
     */
    @Override
    public List<Long> listRolePermission(Long roleId) {
        List<SysRolePermission> list = rolePermissionService.lambdaQuery().eq(SysRolePermission::getRoleId, roleId).list();
        return list.stream().map(item->item.getPermissionId()).collect(Collectors.toList());
    }

    private String getPermUrl(SavePermissionReq req) {
        String permUrl = String.format(GlobalConstants.ADMIN_URL_PERM, req.getMethod(), req.getServiceName(), req.getUrl());
        return permUrl;
    }
}
