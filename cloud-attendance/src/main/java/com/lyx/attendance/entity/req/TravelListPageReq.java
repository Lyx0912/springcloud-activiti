package com.lyx.attendance.entity.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyx.common.base.entity.PageReq;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 黎勇炫
 * @date 2022年11月09日 21:17
 */
@Data
public class TravelListPageReq extends PageReq {
     /**
       * 结果
       */
    private Integer result;
     /**
       * 开始时间
       */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
     /**
       * 结束时间
       */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 是否为管理员
     */
    private Integer manager;
    /**
     * 申请人
     */
    private String uname;
}
