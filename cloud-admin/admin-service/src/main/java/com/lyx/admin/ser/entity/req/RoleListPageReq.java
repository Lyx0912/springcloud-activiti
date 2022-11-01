package com.lyx.admin.ser.entity.req;

import com.lyx.common.base.entity.PageReq;
import lombok.Data;
/**
 * @author： 黎勇炫
 */
@Data
public class RoleListPageReq extends PageReq {
    private String keyword;
}
