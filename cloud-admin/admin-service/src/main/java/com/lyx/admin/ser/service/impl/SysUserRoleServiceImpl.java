package com.lyx.admin.ser.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.admin.ser.entity.SysUserRole;
import com.lyx.admin.ser.mapper.SysUserRoleMapper;
import com.lyx.admin.ser.service.ISysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author： 黎勇炫
 */
@Service
@RequiredArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Override
    public List<Long> selectRoleIds(Long userId) {
        List<SysUserRole> userRoles = lambdaQuery().eq(SysUserRole::getUserId, userId).list();
        if (CollectionUtil.isNotEmpty(userRoles)) {
            return userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void deleteByUserId(Long userId) {
        this.baseMapper.delete(lambdaQuery().eq(SysUserRole::getUserId,userId).getWrapper());
    }
}
