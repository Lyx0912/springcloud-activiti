package com.lyx.attendance.entity.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyx.common.base.entity.PageReq;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 黎勇炫
 * @date 2022年11月04日 22:41
 */
@Data
public class LeaveListPageReq extends PageReq {
    /**
     * 是否为管理员
     */
    private Integer manager;

     /**
       * 请假类型
       */
    private Integer type;
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
     * 结果
     */
    private Integer result;
    /**
     * 申请人
     */
    private String uname;
}
