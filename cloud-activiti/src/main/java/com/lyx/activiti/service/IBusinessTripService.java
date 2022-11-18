package com.lyx.activiti.service;

import com.lyx.common.base.entity.dto.NodeDTO;

import java.util.List;
import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年11月10日 17:17
 */
public interface IBusinessTripService {

     /**
       * 启动一个流程实例
       */
    String startBusinessTripProcessInstance(Map<String, String> params);

}
