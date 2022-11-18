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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getUrlPerm() {
        return urlPerm;
    }

    public void setUrlPerm(String urlPerm) {
        this.urlPerm = urlPerm;
    }

    public String getBtnSign() {
        return btnSign;
    }

    public void setBtnSign(String btnSign) {
        this.btnSign = btnSign;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
