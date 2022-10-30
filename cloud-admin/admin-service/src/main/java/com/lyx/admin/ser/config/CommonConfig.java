package com.lyx.admin.ser.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.lyx.admin.ser.handler.MyMetaObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author： liyongxuan
 */
@Configuration
public class CommonConfig {

    /**
     * 自动填充数据库创建人、创建时间、更新人、更新时间
     */
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
        return globalConfig;
    }
}
