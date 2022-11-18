package com.lyx.admin.ser.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author： 黎勇炫
 */
@Data
@Accessors(chain = true)
public class SysPermissionVO implements Serializable {

    private Long id;

    private String name;

    private Long menuId;

    private String urlPerm;

    private String btnSign;

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
}
