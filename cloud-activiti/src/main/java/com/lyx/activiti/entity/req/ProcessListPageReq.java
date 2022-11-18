package com.lyx.activiti.entity.req;

import com.lyx.common.base.entity.PageReq;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author 黎勇炫
 * @date 2022年11月04日 17:02
 */
@RequiredArgsConstructor
@Data
public class ProcessListPageReq extends PageReq {
    // 关键词
    private String keyword;
}
