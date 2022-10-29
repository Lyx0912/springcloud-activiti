package com.lyx.admin.ser.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
/**
 * @author： 黎勇炫
 */
@Data
@Accessors(chain = true)
@KeySequence(value = "SYS_PERMISSION_SEQ",dbType = DbType.ORACLE)
public class SysPermission extends BaseEntity {

    @TableId(type = IdType.INPUT)
    private Long id;

    private String name;

    private Long menuId;

    private String urlPerm;

    private String btnSign;

    // 有权限的角色编号集合
    @TableField(exist = false)
    private List<String> roles;

}
