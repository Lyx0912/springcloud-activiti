package com.lyx.activiti.entity.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author 黎勇炫
 * @date 2022年11月11日 13:12
 */
@Data
public class TaskHandleReq {

     /**
       * 任务编号
       */
    @NotEmpty(message = "任务编号不能为空")
    private String taskId;
     /**
       * 办理意见
       */
     @NotEmpty(message = "审批意见不能为空")
     private String result;
     /**
       * 批注
       */
    private String opinion;
     /**
       * 流程实例
       */
     @NotEmpty(message = "流程实例编号不能为空")
     private String processInstance;
}
