package com.lyx.auth.securityConfig;

import com.nimbusds.jose.JWSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.text.ParseException;

/**
 * @author 黎勇炫
 * @date 2022年10月11日 11:49
 */
@Configuration
public class TokenConfig {

    @Bean
    @Autowired
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        // Redis存 token一是性能比较好，二是自动过期的机制，符合token的特性
        return new RedisTokenStore(redisConnectionFactory);
    }
}