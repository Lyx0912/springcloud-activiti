package com.lyx.activiti.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 黎勇炫
 * @date 2022年10月24日 18:47
 */
public class Emp  implements Serializable {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 出差申请单名称
     */
    private String holidaysName;
    /**
     * 出差天数
     */
    private Double leaveDay;
    /**
     * 预计开始时间
     */
    private Date beginDate;
    /**
     * 预计结束时间
     */
    private Date endDate;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 出差事由
     */
    private String reson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolidaysName() {
        return holidaysName;
    }

    public void setHolidaysName(String holidaysName) {
        this.holidaysName = holidaysName;
    }

    public Double getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(Double leaveDay) {
        this.leaveDay = leaveDay;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }
}
