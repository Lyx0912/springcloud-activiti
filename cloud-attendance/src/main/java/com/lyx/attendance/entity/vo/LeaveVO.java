package com.lyx.attendance.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 黎勇炫
 * @date 2022年11月04日 22:43
 */
@Data
public class LeaveVO implements Serializable {
    /**
     * 请假编号
     */
    private Long id;

    /**
     * 开始请假时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime startTime;

    /**
     * 请假结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime endTime;

    /**
     * 请假天数
     */
    private Integer leaveDays;

    /**
     * 请假事由
     */
    private String leaveReason;

    /**
     * 类型==>1事假 2病假 3婚假 4丧假 5产假 6探亲 7其他
     */
    private Integer type;

     /**
       * 当前节点名称
       */
    private String currentNode;

    /**
     * 当前审批角色
     */
    private String currentRole;

     /**
       * 流程实例编号
       */
    private String processInstance;
    /**
     * 申请结果
     */
    private Integer result;
    /**
     * 申请人
     */
    private String uname;

}
