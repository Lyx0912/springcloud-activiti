package com.lyx.admin.ser.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * @author： 黎勇炫
 */
@Data
@KeySequence(value = "SYS_ROLE_SEQ",dbType = DbType.ORACLE)
public class SysRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    /**
    * id
    */
    private Long id;

    /**
    * 角色名称
    */
    private String name;

    /**
    * 角色编码
    */
    private String code;

    /**
    * 显示顺序
    */
    private Integer sort;

    /**
    * 角色状态：0-正常；1-停用
    */
    private int status;

    /**
    * 逻辑删除标识：0-未删除；1-已删除
    */
    @TableLogic(value = "0", delval = "1")
    private int deleted;

    public SysRole() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}