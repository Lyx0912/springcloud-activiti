package com.lyx.attendance.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 黎勇炫
 * @date 2022年11月09日 21:19
 */
@Data
public class TravelVO {

    /**
     * 出差编号
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 出差时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime endTime;

    /**
     * 出差天数
     */
    private Integer travelDays;

    /**
     * 出差事由
     */
    private String travelReason;

    /**
     * 请假人姓名
     */
    private String uname;

    /**
     * 流程实例编号
     */
    private String processInstance;

    /**
     * 申请结果
     */
    private Integer result;

    /**
     * 当前节点名称
     */
    private String currentNode;
    /**
     * 当前审批角色
     */
    private String currentRole;
}
