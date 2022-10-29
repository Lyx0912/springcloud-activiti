package com.lyx.admin.ser.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author： 黎勇炫
 */
@Data
public class BaseEntity {

    @TableField(value = "CREATE_TIME",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "UPDATE_TIME",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "CREATE_BY",fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(value = "UPDATE_BY",fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
}
