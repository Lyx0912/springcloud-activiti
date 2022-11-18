package com.lyx.activiti.service;

import com.lyx.activiti.entity.vo.ProcessInstanceVO;
import com.lyx.common.base.entity.dto.NodeDTO;

import java.util.List;
import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年11月07日 21:53
 */
public interface IApplyLeaveService {
    /**
     * 启动一个请假实例
     *
     * @param params
     * @return void
     * @author 黎勇炫
     * @create 2022/11/7
     * @email 1677685900@qq.com
     */
    String startLeaveProcessInstance(Map<String, String> params);

}
