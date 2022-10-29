package com.lyx.admin.ser.entity.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author： 黎勇炫
 */
@Data
@Accessors(chain = true)
public class SavePermissionReq implements Serializable {

    private Long id;

    @NotBlank(message = "name 不为空")
    private String name;
    @NotNull(message = "menuId 不为空")
    private Long menuId;
    @NotBlank(message = "method 不为空")
    private String method;
    @NotBlank(message = "serviceName 不为空")
    private String serviceName;
    @NotBlank(message = "url 不为空")
    private String url;

}
