package com.lyx.activiti.entity.req;

import lombok.Data;

/**
 * @author 黎勇炫
 * @date 2022年11月05日 13:56
 */
@Data
public class ModelSaveReq {

     /**
       * 模型名称
       */
    private String name;

     /**
       * 模型标识
       */
     private String key;

     /**
       * 分类
       */
     private String category;

     /**
       * 版本
       */
     private Integer version;

     /**
       * 描述
       */
     private String description;

      /**
        * xml
        */
     private String xml;

}
