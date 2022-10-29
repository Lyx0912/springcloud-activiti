package com.lyx.admin.ser.entity.req;

import com.lyx.admin.ser.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author： 黎勇炫
 */
@Data
public class SaveMenuReq extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * id
    */
    private Long id;

    /**
    * 菜单名称
    */
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    /**
    * 父菜单id
    */
    @NotNull(message = "父级菜单不能为空")
    private Long parentId;

    /**
    * 路由路径
    */
    @NotBlank(message = "路径不能为空")
    private String path;

    /**
    * 组件路径
    */
    @NotBlank(message = "组件不能为空")
    private String component;

    /**
    * 菜单图标
    */
    private String icon;

    /**
    * 排序
    */
    @NotNull(message = "排序不能为空")
    private Integer sort;

    /**
    * 状态：0-禁用 1-开启
    */
    private int visible;

    /**
    * 跳转路径
    */

    private String redirect;


    public SaveMenuReq() {}
}