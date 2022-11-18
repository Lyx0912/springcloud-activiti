package com.lyx.attendance;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;

/**
 * @author 黎勇炫
 * @date 2022年11月03日 18:13
 */
@SpringBootApplication
@EnableFeignClients
public class AttendanceApp {
    public static void main(String[] args) {
        SpringApplication.run(AttendanceApp.class, args);
    }
}
