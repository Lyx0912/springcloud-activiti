package com.lyx.admin.ser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyx.admin.ser.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @author： 黎勇炫
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> listPermRoles();
}
