package com.lyx.admin.ser.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.admin.ser.config.AdminMapStruct;
import com.lyx.admin.ser.entity.SysRole;
import com.lyx.admin.ser.entity.vo.SysRoleVO;
import com.lyx.admin.ser.mapper.SysRoleMapper;
import com.lyx.admin.ser.service.ISysRoleService;
import com.lyx.common.base.constant.GlobalConstants;
import lombok.RequiredArgsConstructor;
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

    @Override
    public void updateStatus(Long id, int status) {

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
}
