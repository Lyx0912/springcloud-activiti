package com.lyx.activiti.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 黎勇炫
 * @date 2022年11月04日 18:44
 */
@Data
public class ModelVO {

     /**
       * 编号
       */
    private String id;
     /**
       * 模型名称
       */
    private String name;
     /**
       * 模型标识
       */
    private String key;
     /**
       * 模型分类
       */
    private String category;
     /**
       * 创建时间
       */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
     /**
       * 最近更新时间
       */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;
     /**
       * 部署编号
       */
    private String deploymentId;
}
