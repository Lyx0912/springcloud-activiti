package com.lyx.attendance.entity.req;

import com.lyx.common.base.entity.PageReq;
import lombok.Data;

/**
 * @author 黎勇炫
 * @date 2022年11月11日 21:45
 */
@Data
public class RecordSelectPageReq extends PageReq {

    private String userName;
}
