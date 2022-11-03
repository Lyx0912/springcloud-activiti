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
@TableName("ATT_OVERWORK")
public class Overwork implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private BigDecimal id;

    /**
     * 用户id
     */
    private BigDecimal userId;

    /**
     * 开始加班时间
     */
    private LocalDateTime startTime;

    /**
     * 加班结束时间
     */
    private LocalDateTime endTime;

    /**
     * 加班市场
     */
    private String duration;

    /**
     * 备注
     */
    private String remark;

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
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Overwork{" +
            "id=" + id +
            ", userId=" + userId +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", duration=" + duration +
            ", remark=" + remark +
        "}";
    }
}
