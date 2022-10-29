package com.lyx.admin.ser.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author： 黎勇炫
 */
@Data
public class SysMenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Long id;
    /**
     * 父级菜单id
     */
    private Long parentId;

    /**
    * 菜单名称
    */
    private String name;

    /**
    * 路由路径
    */
    private String path;

    /**
    * 组件路径
    */
    private String component;

    /**
    * 菜单图标
    */
    private String icon;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 状态：0-禁用 1-开启
    */
    private int visible;

    /**
    * 跳转路径
    */
    private String redirect;

    /**
     * 子节点
     */
    private List<SysMenuVO> children;


    public SysMenuVO() {}
}