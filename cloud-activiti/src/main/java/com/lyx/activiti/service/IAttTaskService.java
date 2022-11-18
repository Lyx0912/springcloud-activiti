package com.lyx.activiti.service;

import com.lyx.activiti.entity.req.TaskHandleReq;
import com.lyx.activiti.entity.req.TaskListPageReq;
import com.lyx.activiti.entity.vo.ProcessInstanceVO;
import com.lyx.activiti.entity.vo.TaskVO;
import com.lyx.common.base.entity.PageUtils;
import com.lyx.common.base.entity.dto.NodeDTO;

import java.util.List;

/**
 * @author 黎勇炫
 * @date 2022年11月09日 13:50
 */
public interface IAttTaskService {

    /**
     * 查询任务列表
     * @return java.util.List<com.lyx.activiti.entity.vo.TaskVO>
     * @author 黎勇炫
     * @create 2022/11/9
     * @email 1677685900@qq.com
     */
    PageUtils<TaskVO> getTaskListByRoles(TaskListPageReq req);

    /**
     * 构建节点信息
     * @param processDefinitionKey
     * @param ids
     * @return java.util.List<com.lyx.common.base.entity.dto.NodeDTO>
     * @author 黎勇炫
     * @create 2022/11/10
     * @email 1677685900@qq.com       
     */
    public List<NodeDTO> buildNodeInfo(String processDefinitionKey, List<Long> ids);

    /**
     * 根据流程实例编号查询历史任务完成进度和情况
     * @param instanceId
     * @return java.util.List<com.lyx.activiti.entity.vo.ProcessInstanceVO>
     * @author 黎勇炫
     * @create 2022/11/8
     * @email 1677685900@qq.com
     */
    List<ProcessInstanceVO> processInstanceHistory(String instanceId);

     /**
       * 办理任务
       */
    void handleTask(TaskHandleReq req);
}
