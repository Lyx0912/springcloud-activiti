package com.lyx.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黎勇炫
 * @date 2022年10月20日 22:05
 */
public class TestActiviti {

    public void testActiviti(){
        //创建ProcessEngineConfiguration
        ProcessEngineConfiguration configuration =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //通过ProcessEngineConfiguration创建ProcessEngine，此时会创建数据库
        ProcessEngine processEngine = configuration.buildProcessEngine();
        // 使用RepositoryService进行部署
        Deployment deployment = processEngine.getRepositoryService().createDeployment()
                .addClasspathResource("bpmn/leave-uel.bpmn20.xml")
                .name("请假流程")
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
        uel.put("emp","liyongxuan");
        uel.put("kz","zhangkezhang");
        uel.put("bz","wangbz");
        uel.put("cto","CTO");
        // 启动一个请假实例，开始请假流程
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById("leave:3:12503",uel);
        // 输出内容
        System.out.println("流程定义id："+processInstance.getProcessDefinitionId());
        System.out.println("流程实例id："+processInstance.getId());
        System.out.println("活动编号"+processInstance.getActivityId());
    }

     /**
       * 查询流程
       */
    public void queryTask(){
        ProcessEngineConfiguration configuration =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();
        // 创建任务查询器
        TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery().processDefinitionId("leave:3:12503").taskAssignee("liyongxuan");
        List<Task> list = taskQuery.list();
        // 遍历任务
        list.forEach(item->{ 
            System.out.println("任务编号："+item.getId());
            System.out.println("流程执行人："+item.getAssignee());
            System.out.println("任务名称："+item.getName());
            System.out.println("激活状态："+item.isSuspended());
        });
    }

     /**
       * 完成个人任务
       */
     public void completeTask(){
         ProcessEngineConfiguration configuration =
                 ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
         ProcessEngine processEngine = configuration.buildProcessEngine();
         // 获取任务服务
         TaskService taskService = processEngine.getTaskService();
         Task task = taskService.createTaskQuery().processDefinitionId("leave:3:12503").taskAssignee("liyongxuan").singleResult();
         // 完成任务
         taskService.complete(task.getId());
     }


      /**
        * 查询流程部署信息
        */
     public void queryDeployment(){
         ProcessEngineConfiguration configuration =
                 ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
         ProcessEngine processEngine = configuration.buildProcessEngine();
         // 获取资源服务
         RepositoryService repositoryService = processEngine.getRepositoryService();
         // 获取查询器
         ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
         // 开始查询
         List<ProcessDefinition> leave = processDefinitionQuery.processDefinitionKey("leave").list();
         leave.forEach(item->{
             System.out.println("ID:"+item.getId());
             System.out.println("NAME:"+item.getName());
             System.out.println("getDeploymentId:"+item.getDeploymentId());
         });
     }

      /**
        * 下载资源
        */
    public void downloadResource() throws IOException {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
          ProcessEngine processEngine = configuration.buildProcessEngine();
          // 获取资源服务
          RepositoryService repositoryService = processEngine.getRepositoryService();
          // 获取流程定义信息
          ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
          ProcessDefinition definition = query.processDefinitionId("leave:2:5003").singleResult();
          InputStream xmlStream = repositoryService.getResourceAsStream(definition.getDeploymentId(),definition.getResourceName());
          OutputStream xmlOutputStream = new FileOutputStream(new File("E:\\leave.bpmn20.xml"));
          IOUtils.copy(xmlStream,xmlOutputStream);
          xmlStream.close();
          xmlOutputStream.close();
      }

       /**
         * 历史任务查询
         */
      public void historyTaskQuery(){
          ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
          ProcessEngine processEngine = configuration.buildProcessEngine();
          // 获取历史服务
          HistoryService historyService = processEngine.getHistoryService();
          // 获取查询器
          HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
          // 查询任务
          List<HistoricActivityInstance> list = historicActivityInstanceQuery.processDefinitionId("leave:2:5003").orderByHistoricActivityInstanceStartTime().asc().list();
          // 遍历任务列表
          list.forEach(item->{
              System.out.println("实例编号："+item.getId());
              System.out.println("任务编号："+item.getTaskId());
              System.out.println("任务执行人："+item.getAssignee());
              System.out.println("开始时间："+item.getStartTime());
              System.out.println("结束时间："+item.getEndTime());
              System.out.println("==============================================>");
          });
      }
      
     /**
       * 挂起流程
       */
    public void suspendProcess(){
         ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
         ProcessEngine processEngine = configuration.buildProcessEngine();
         // 获取资源服务
         RepositoryService repositoryService = processEngine.getRepositoryService();
         ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionId("leave:2:5003").singleResult();
         boolean suspended = definition.isSuspended();
         // 判断是否挂起
         if(suspended){
             // 挂起的-》激活
             repositoryService.activateProcessDefinitionByKey(definition.getKey());
             System.out.println("激活流程："+definition.getKey());
         }else {
             repositoryService.suspendProcessDefinitionByKey(definition.getKey());
             System.out.println("挂起流程："+definition.getKey());
         }
     }

    /**
     * 挂起单个流程
     */
    public void suspendTask(){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();
        // 获取资源服务
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId("7501").singleResult();
        boolean suspended = instance.isSuspended();
        // 判断是否挂起
        if(suspended){
            // 挂起的-》激活
            runtimeService.activateProcessInstanceById(instance.getProcessInstanceId());
            System.out.println("激活任务："+instance.getProcessInstanceId());
        }else {
            runtimeService.suspendProcessInstanceById(instance.getProcessInstanceId());
            System.out.println("挂起任务："+instance.getProcessInstanceId());
        }
    }
}
