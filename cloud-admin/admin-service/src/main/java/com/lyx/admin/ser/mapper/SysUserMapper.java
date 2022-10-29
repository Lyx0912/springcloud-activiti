package com.lyx.admin.ser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyx.admin.dto.UserAuthDTO;
import com.lyx.admin.ser.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author： 黎勇炫
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    UserAuthDTO getByUsername(@Param("userName") String userName);
}
