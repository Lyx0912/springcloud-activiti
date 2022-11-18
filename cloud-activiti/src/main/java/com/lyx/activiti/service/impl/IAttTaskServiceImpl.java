package com.lyx.activiti.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.lyx.activiti.entity.req.TaskHandleReq;
import com.lyx.activiti.entity.req.TaskListPageReq;
import com.lyx.activiti.entity.vo.ModelVO;
import com.lyx.activiti.entity.vo.ProcessInstanceVO;
import com.lyx.activiti.entity.vo.TaskVO;
import com.lyx.activiti.service.IAttTaskService;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.entity.dto.NodeDTO;
import com.lyx.common.web.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 黎勇炫
 * @date 2022年11月09日 13:50
 */
@Service
@RequiredArgsConstructor
public class IAttTaskServiceImpl implements IAttTaskService {

    private final TaskService taskService;
    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;
    private final HistoryService historyService;
    /**
     * 查询任务列表
     *
     * @return java.util.List<com.lyx.activiti.entity.vo.TaskVO>
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    @Override
    public PageUtils<TaskVO> getTaskListByRoles(TaskListPageReq req) {
        PageUtils<TaskVO> page = new PageUtils<>();
        List<String> roles = UserContext.getRoleList();
        List<TaskVO> vos = new ArrayList<>();
        // 遍历该用户的角色,添加assignee
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> taskList = taskQuery.taskAssigneeIds(roles).listPage((int) ((req.getPageNo() - 1) * req.getPageSize()), req.getPageSize().intValue());
        // 遍历任务列表
        taskList.forEach(task->{
            TaskVO vo = new TaskVO();
            BeanUtils.copyProperties(task,vo);
            vo.setProcessDefnitionId(task.getProcessDefinitionId());
            vo.setBussinessKey(task.getBusinessKey());
            vo.setProcessDefnitionName(repositoryService.getProcessDefinition(task.getProcessDefinitionId()).getName());
            vos.add(vo);
        });
        page.setList(vos);
        page.setTotal(taskQuery.count());
        return page;
    }

    /**
     * 构建任务节点信息
     *
     * @param ids 请假信息的id列表
     */
    @Override
    public List<NodeDTO> buildNodeInfo(String processDefinitionKey, List<Long> ids) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<NodeDTO> dtos = ids.stream().map(busId -> {
            String lId = String.valueOf(busId);
            NodeDTO dto = new NodeDTO();
            // 查询对应的任务信息 如果不存在任务就代表已经完成
            Task task = taskQuery.processInstanceBusinessKey(lId).processDefinitionKey(processDefinitionKey).singleResult();
            if (task == null) {
                dto.setCurrentNode("已结束");
                dto.setCurrentRole("无");
                // 如果存在就存入节点和审核人信息
            } else {
                dto.setCurrentNode(task.getName());
                dto.setCurrentRole(task.getAssignee());
            }
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

    /**
     * 根据流程实例编号查询历史任务完成进度和情况
     *
     * @param instanceId
     * @return java.util.List<com.lyx.activiti.entity.vo.ProcessInstanceVO>
     * @author 黎勇炫
     * @create 2022/11/8
     * @email 1677685900@qq.com
     */
    @Override
    public List<ProcessInstanceVO> processInstanceHistory(String instanceId) {
        List<HistoricTaskInstance> instance = historyService.createHistoricTaskInstanceQuery().processInstanceId(instanceId).orderByHistoricTaskInstanceStartTime().asc().list();
        if(CollectionUtil.isNotEmpty(instance)){
            return instance.stream().map(item -> {
                ProcessInstanceVO vo = new ProcessInstanceVO();
                List<Comment> taskComments = taskService.getTaskComments(item.getId());
                if(CollectionUtil.isNotEmpty(taskComments)){
                    vo.setComment(taskComments.get(0).getFullMessage());
                }
                BeanUtils.copyProperties(item, vo);
                return vo;
            }).collect(Collectors.toList());
        }

        return Collections.EMPTY_LIST;
    }

    /**
     * 办理任务
     * @param req
     * @return void
     * @author 黎勇炫
     * @create 2022/11/11
     * @email 1677685900@qq.com
     */
    @Override
    public void handleTask(TaskHandleReq req) {
        // 如果批注不为空就添加批注
        if(StringUtils.isNotEmpty(req.getOpinion())) {
            taskService.addComment(req.getTaskId(),req.getProcessInstance(),req.getOpinion());
        }
        Map<String,String> map = new HashMap<>();
        map.put("result",req.getResult());
        // 注入审批结果
        taskService.setVariables(req.getTaskId(),map);
        // 完成任务
        taskService.complete(req.getTaskId());
    }
}
