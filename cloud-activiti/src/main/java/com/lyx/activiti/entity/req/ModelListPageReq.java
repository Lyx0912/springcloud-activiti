package com.lyx.activiti.entity.req;

import com.lyx.common.base.entity.PageReq;
import lombok.Data;

/**
 * @author 黎勇炫
 * @date 2022年11月04日 18:43
 */
@Data
public class ModelListPageReq extends PageReq {

    private String keyword;
}
