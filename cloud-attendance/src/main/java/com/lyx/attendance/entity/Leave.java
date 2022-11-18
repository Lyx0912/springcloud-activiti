package com.lyx.attendance.entity;

import com.baomidou.mybatisplus.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 黎勇炫
 * @since 2022-11-03
 */
@TableName("ATT_LEAVE")
@KeySequence(value = "ATT_LEAVE_SEQ",dbType = DbType.ORACLE)
public class Leave  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请假编号
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

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
     * 申请结果
     */
    private Integer result;

     /**
       * 请假人姓名
       */
    private String uname;

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

    public Integer getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(Integer leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
