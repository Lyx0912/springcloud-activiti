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
@TableName("ATT_RECORD")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出勤编号
     */
    private BigDecimal id;

    /**
     * 用户编号
     */
    private BigDecimal userId;

    /**
     * 上班打卡时间
     */
    private LocalDateTime startTime;

    /**
     * 下班打卡时间
     */
    private LocalDateTime endTime;

    /**
     * 出勤记录类型
     */
    private Integer status;

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
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Record{" +
            "id=" + id +
            ", userId=" + userId +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", status=" + status +
        "}";
    }
}
