package com.lyx.activiti;

import com.lyx.activiti.domain.Emp;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年10月24日 18:24
 */
public class TestVariable {

     /**
       * 部署带分支的流程图
       */
     public void testActiviti(){
         //创建ProcessEngineConfiguration
         ProcessEngineConfiguration configuration =
                 ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
         //通过ProcessEngineConfiguration创建ProcessEngine，此时会创建数据库
         ProcessEngine processEngine = configuration.buildProcessEngine();
         // 使用RepositoryService进行部署
         Deployment deployment = processEngine.getRepositoryService().createDeployment()
                 .addClasspathResource("bpmn/leave-inclusive.bpmn20.xml")
                 .name("请假流程(并行网关)")
                 .deploy();

         // 4、输出部署信息
         System.out.println("流程部署id：" + deployment.getId());
         System.out.println("流程部署名称：" + deployment.getName());
     }

    /**
     * 启动一个流程实例
     */
    public void startInstance(){
        //创建ProcessEngineConfiguration
        ProcessEngineConfiguration configuration =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //通过ProcessEngineConfiguration创建ProcessEngine，此时会创建数据库
        ProcessEngine processEngine = configuration.buildProcessEngine();
        // 创建一个存放uel变量的map
        Map<String,Object> uel = new HashMap<>();
        uel.put("day",5);
        // 启动一个请假实例，开始请假流程
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById("leave-inclusive:2:80003",uel);
        // 输出内容
        System.out.println("流程定义id："+processInstance.getProcessDefinitionId());
        System.out.println("流程实例id："+processInstance.getId());
        System.out.println("活动编号"+processInstance.getActivityId());
    }

    /**
     * 完成一个实例
     */
    public void completInstance(){
        //创建ProcessEngineConfiguration
        ProcessEngineConfiguration configuration =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //通过ProcessEngineConfiguration创建ProcessEngine，此时会创建数据库
        ProcessEngine processEngine = configuration.buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionId("leave-inclusive:2:80003").taskAssignee("rs").list();
        list.forEach(item->{
            System.out.println("查询到任务："+item);
            taskService.complete(item.getId());
            System.out.println("完成任务");
        });
    }

     /**
       * 组任务->拾取任务
       */
     public void testTaskInstance(){
         //创建ProcessEngineConfiguration
         ProcessEngineConfiguration configuration =
                 ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
         //通过ProcessEngineConfiguration创建ProcessEngine，此时会创建数据库
         ProcessEngine processEngine = configuration.buildProcessEngine();
         TaskService taskService = processEngine.getTaskService();
         // 查询一个任务
         Task task = taskService.createTaskQuery().taskCandidateUser("zkz").processDefinitionId("leave-group:1:40003").singleResult();
         if(task != null){
             taskService.claim(task.getId(),"zkz");
             System.out.println("拾取任务成功");
         }
     }

}
