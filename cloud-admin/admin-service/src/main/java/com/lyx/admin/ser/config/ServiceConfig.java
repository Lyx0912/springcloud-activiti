package com.lyx.admin.ser.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黎勇炫
 * @date 2022年10月30日 11:19
 */
@Configuration
@ConfigurationProperties(prefix = "admin")
@Data
public class ServiceConfig {

     /**
       * 服务列表
       */
    private List<String> services;

}
