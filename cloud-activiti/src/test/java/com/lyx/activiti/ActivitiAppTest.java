package com.lyx.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年10月26日 17:50
 */
@SpringBootTest
public class ActivitiAppTest {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;

    @Test
    public void deplomentProcess(){
        DeploymentBuilder builder = repositoryService.createDeployment();
        Deployment deploy = builder.addClasspathResource("bpmn/leave-boot.bpmn20.xml")
                .name("请假流程(Springboot版3)")
                .deploy();

        // 4、输出部署信息
        System.out.println("流程部署id：" + deploy.getId());
        System.out.println("流程部署名称：" + deploy.getName());
    }

    @Test
    public void queryTask(){

        // 创建任务查询器
        TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionId("leave:1:122506").taskAssignee("manager");
        List<Task> list = taskQuery.list();
        // 遍历任务
        list.forEach(item->{
            Map<String,String> map = new HashMap<>();
            map.put("result","0");
            taskService.setVariables(item.getId(),map);
            taskService.complete(item.getId());
        });
    }
}
