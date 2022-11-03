package com.lyx.attendance;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;

/**
 * @author 黎勇炫
 * @date 2022年11月03日 18:13
 */
@SpringBootApplication
public class AttendanceApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AttendanceApp.class, args);
        FastAutoGenerator.create("jdbc:oracle:thin:@42.192.229.95:1521:helowin", "lyx", "Liyongxuan666")
                .globalConfig(builder -> {
                    builder.author("黎勇炫") // 设置作者
                            .outputDir("D:\\code\\WorkSpace\\lyx-cloud\\springcloud-activiti\\cloud-attendance\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.lyx.attendance") // 设置父包名
                            .moduleName("attendance") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.controller, "D:\\code\\WorkSpace\\lyx-cloud\\springcloud-activiti\\cloud-attendance\\src\\main\\java\\com\\lyx\\attendance\\controller"))
                            .pathInfo(Collections.singletonMap(OutputFile.entity, "D:\\code\\WorkSpace\\lyx-cloud\\springcloud-activiti\\cloud-attendance\\src\\main\\java\\com\\lyx\\attendance\\entity"))
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "D:\\code\\WorkSpace\\lyx-cloud\\springcloud-activiti\\cloud-attendance\\src\\main\\java\\com\\lyx\\attendance\\mapeper"))
                            .pathInfo(Collections.singletonMap(OutputFile.service, "D:\\code\\WorkSpace\\lyx-cloud\\springcloud-activiti\\cloud-attendance\\src\\main\\java\\com\\lyx\\attendance\\service"))
                            .pathInfo(Collections.singletonMap(OutputFile.serviceImpl, "D:\\code\\WorkSpace\\lyx-cloud\\springcloud-activiti\\cloud-attendance\\src\\main\\java\\com\\lyx\\attendance\\service\\impl"))
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\code\\WorkSpace\\lyx-cloud\\springcloud-activiti\\cloud-attendance\\src\\main\\resource\\mapper"));
                // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("ATT_LEAVE","ATT_OVERWORK","ATT_RECORD","ATT_TRAVEL") // 设置需要生成的表名
                            .addTablePrefix("ATT_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
