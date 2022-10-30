package com.lyx.admin.ser.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
/**
 * @author： 黎勇炫
 */
@Data
public class SysMenuSelectVO {
    private Long id;
    private String label;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SysMenuSelectVO> children;

}
