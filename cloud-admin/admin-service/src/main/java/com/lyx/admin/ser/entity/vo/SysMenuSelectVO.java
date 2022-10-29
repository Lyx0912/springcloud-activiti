package com.lyx.admin.ser.entity.vo;

import lombok.Data;

import java.util.List;
/**
 * @author： 黎勇炫
 */
@Data
public class SysMenuSelectVO {
    private Long id;
    private String label;
    private List<SysMenuSelectVO> children;

}
