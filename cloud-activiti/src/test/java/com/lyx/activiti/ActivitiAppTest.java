package com.lyx.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 黎勇炫
 * @date 2022年10月26日 17:50
 */
@SpringBootTest
public class ActivitiAppTest {

    @Autowired
    private RepositoryService repositoryService;

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
}
