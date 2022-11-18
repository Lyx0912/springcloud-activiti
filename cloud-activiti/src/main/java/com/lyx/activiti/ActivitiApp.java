package com.lyx.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 黎勇炫
 * @date 2022年10月26日 15:40
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class ActivitiApp {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiApp.class,args);
    }
}
