package com.lyx.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 黎勇炫
 * @date 2022年10月26日 15:40
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class ActivitiApp {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiApp.class,args);
    }
}
