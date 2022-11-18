package com.lyx.attendance.entity.req;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author 黎勇炫
 * @date 2022年11月12日 0:46
 */
@Data
public class OverworkExcelReq {

    /**
     * 用户id
     */
    @ExcelProperty(index = 0)
    private Long userId;
    /**
     * 用户名
     */
    @ExcelProperty(index = 1)
    private String userName;

    /**
     * 开始加班时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(index = 2)
    private String startTime;

    /**
     * 加班结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(index = 3)
    private String endTime;

    /**
     * 备注
     */
    @ExcelProperty(index = 4)
    private String remark;

    /**
     * 加班时长
     */
    private String duration;

}
