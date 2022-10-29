package com.lyx.admin.ser.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author： 黎勇炫
 */
@Data
public class SysRoleSelectVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * id
    */
    private Long id;

    /**
    * 角色名称
    */
    private String name;

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
}