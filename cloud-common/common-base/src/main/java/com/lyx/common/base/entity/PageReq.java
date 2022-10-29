package com.lyx.common.base.entity;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
/**
 * @author： 黎勇炫
 */
@Data
public class PageReq implements Serializable {
    private Long pageNo;
    private Long pageSize;
}
