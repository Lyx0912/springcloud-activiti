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
@TableName("ATT_TRAVEL")
public class Travel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出差编号
     */
    private BigDecimal id;

    /**
     * 用户id
     */
    private BigDecimal userId;

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
    private BigDecimal travelDays;

    /**
     * 出差事由
     */
    private String travelReason;

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
    public BigDecimal getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(BigDecimal travelDays) {
        this.travelDays = travelDays;
    }
    public String getTravelReason() {
        return travelReason;
    }

    public void setTravelReason(String travelReason) {
        this.travelReason = travelReason;
    }

    @Override
    public String toString() {
        return "Travel{" +
            "id=" + id +
            ", userId=" + userId +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", travelDays=" + travelDays +
            ", travelReason=" + travelReason +
        "}";
    }
}
