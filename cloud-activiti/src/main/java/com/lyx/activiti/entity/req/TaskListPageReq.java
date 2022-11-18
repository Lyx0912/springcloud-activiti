package com.lyx.activiti.entity.req;

import com.lyx.common.base.entity.PageReq;
import lombok.Data;

/**
 * @author 黎勇炫
 * @date 2022年11月09日 17:22
 */
@Data
public class TaskListPageReq extends PageReq {

    private String keyword;
}
