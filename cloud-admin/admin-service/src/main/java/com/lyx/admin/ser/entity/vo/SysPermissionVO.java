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

}
