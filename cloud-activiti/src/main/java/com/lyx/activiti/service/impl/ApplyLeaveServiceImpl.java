package com.lyx.activiti.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.lyx.activiti.constant.ActivitiConstant;
import com.lyx.activiti.entity.vo.ProcessInstanceVO;
import com.lyx.activiti.service.IApplyLeaveService;
import com.lyx.common.base.entity.dto.NodeDTO;
import com.lyx.common.base.result.ResultCode;
import com.lyx.common.base.utils.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 黎勇炫
 * @date 2022年11月07日 21:54
 */
@Service
@RequiredArgsConstructor
public class ApplyLeaveServiceImpl implements IApplyLeaveService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final HistoryService historyService;

    /**
     * 启动一个请假实例
     *
     * @param params 参数
     * @return void
     * @author 黎勇炫
     * @create 2022/11/7
     * @email 1677685900@qq.com
     */
    @Override
    public String startLeaveProcessInstance(Map<String, String> params) {
        // 获取变量
        Map<String,Object> properties = new HashMap<>();
        // 业务key
        String businessKey = params.get("leaveId");
        // emp变量
        String emp = params.get("emp");
        properties.put("emp",params.get("emp"));
        // 获取流程定义
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(ActivitiConstant.LEAVE_DEFINITION_KEY,businessKey,properties);
        AssertUtil.notEmpty(instance, ResultCode.PROCESS_NOT_EXIST);
        // 完成申请单任务
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(params.get("leaveId")).taskAssignee(emp).singleResult();
        taskService.complete(task.getId());
        return instance.getProcessInstanceId();
    }

}
