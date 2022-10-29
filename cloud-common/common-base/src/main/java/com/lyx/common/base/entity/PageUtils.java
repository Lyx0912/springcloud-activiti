package com.lyx.common.base.entity;

import lombok.Data;

import java.util.List;
/**
 * @author： 黎勇炫
 */
@Data
public class PageUtils<T> {
    private Long total;
    private Long pageNo;
    private Long pageSize;
    private List<T> list;
}
