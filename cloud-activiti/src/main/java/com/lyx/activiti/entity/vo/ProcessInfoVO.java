package com.lyx.activiti.entity.vo;

import lombok.Data;

/**
 * @author 黎勇炫
 * @date 2022年11月04日 17:10
 */
@Data
public class ProcessInfoVO {

    // 流程定义ID
    private String id;
    // 流程部署id
    private String deploymentId;
    // 流程定义Key
    private String key;
    // 流程名称
    private String name;
    // 资源定义
    private String resourceName;
    // 流程状态 1正常 2挂起
    private Integer status;


}
