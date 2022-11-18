package com.lyx.activiti.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 黎勇炫
 * @date 2022年11月09日 13:51
 */
@Data
public class TaskVO {
     /**
       * 任务编号
       */
    private String id;
     /**
       * 实例编号
       */
    private String processInstanceId;
     /**
       * 流程编号
       */
    private String processDefnitionId;
     /**
       * 流程名称
       */
    private String processDefnitionName;
     /**
       * 名称
       */
    private String name;
     /**
       * 业务key
       */
    private String bussinessKey;
     /**
       * 办理职称
       */
    private String assignee;
     /**
       * 创建时间
       */
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createTime;
}
