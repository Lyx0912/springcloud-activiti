package com.lyx.attendance.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
@TableName("ATT_TRAVEL")
@KeySequence(value = "ATT_LEAVE_SEQ",dbType = DbType.ORACLE)
public class Travel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出差编号
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 出差时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
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
       * 申请结果
       */
    private Integer result;

     /**
       * 流程实例编号
       */
    private String processInstance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(Integer travelDays) {
        this.travelDays = travelDays;
    }

    public String getTravelReason() {
        return travelReason;
    }

    public void setTravelReason(String travelReason) {
        this.travelReason = travelReason;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(String processInstance) {
        this.processInstance = processInstance;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
