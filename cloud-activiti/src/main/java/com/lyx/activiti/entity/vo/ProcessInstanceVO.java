package com.lyx.activiti.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 黎勇炫
 * @date 2022年11月08日 21:21
 */
@Data
public class ProcessInstanceVO implements Serializable {

     /**
       * 节点名称
       */
    private String name;
     /**
       * 办理人(角色)
       */
    private String assignee;
    /**
     * 开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
     /**
       * 办理时间
       */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
     /**
       * 审批意见
       */
    private String comment;
}
