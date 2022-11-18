package com.lyx.activiti.service.impl;

import com.lyx.activiti.constant.ActivitiConstant;
import com.lyx.activiti.service.IBusinessTripService;
import com.lyx.common.base.entity.dto.NodeDTO;
import com.lyx.common.base.result.ResultCode;
import com.lyx.common.base.utils.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年11月10日 17:18
 */
@Service
@RequiredArgsConstructor
public class BusinessTripServiceImpl implements IBusinessTripService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    /**
     * 启动一个流程实例
     *
     * @param params
     */
    @Override
    public String startBusinessTripProcessInstance(Map<String, String> params) {
        // 获取变量
        Map<String,Object> properties = new HashMap<>();
        // 业务key
        String businessKey = params.get("travelId");
        // emp变量
        String emp = params.get("emp");
        properties.put("emp",params.get("emp"));
        // 获取流程定义
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(ActivitiConstant.BUSINESS_TRIP_DEFINITION_KEY,businessKey,properties);
        AssertUtil.notEmpty(instance, ResultCode.PROCESS_NOT_EXIST);
        // 完成申请单任务
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).taskAssignee(emp).singleResult();
        taskService.complete(task.getId());
        return instance.getProcessInstanceId();
    }

}
