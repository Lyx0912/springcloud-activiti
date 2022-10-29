package com.lyx.admin.ser.entity.req;

import com.lyx.admin.ser.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author： 黎勇炫
 */
@Data
public class SaveSysRoleReq extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */

    private Long id;

    /**
    * 角色名称
    */
    @NotBlank(message = "角色名称不能为空")
    private String name;

    /**
    * 角色编码
    */
    @NotBlank(message = "角色编码不能为空")
    private String code;

    /**
    * 显示顺序
    */
    @NotNull(message = "排序不能为空")
    private Integer sort;

    /**
    * 角色状态：0-正常；1-停用
    */
    private int status;

    /**
    * 逻辑删除标识：0-未删除；1-已删除
    */
    private int deleted;

    public SaveSysRoleReq() {}
}