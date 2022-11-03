package com.lyx.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("ATT_LEAVE")
public class Leave implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请假编号
     */
    private BigDecimal id;

    /**
     * 用户id
     */
    private BigDecimal userId;

    /**
     * 开始请假时间
     */
    private LocalDateTime startTime;

    /**
     * 请假结束时间
     */
    private LocalDateTime endTime;

    /**
     * 请假天数
     */
    private BigDecimal leaveDays;

    /**
     * 请假事由
     */
    private String leaveReason;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
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
    public BigDecimal getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(BigDecimal leaveDays) {
        this.leaveDays = leaveDays;
    }
    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    @Override
    public String toString() {
        return "Leave{" +
            "id=" + id +
            ", userId=" + userId +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", leaveDays=" + leaveDays +
            ", leaveReason=" + leaveReason +
        "}";
    }
}
