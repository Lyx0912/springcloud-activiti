package com.lyx.gateway.secutity;

import cn.hutool.core.util.StrUtil;
import com.lyx.common.base.constant.SecurityConstants;
import com.nimbusds.jose.JWSObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.text.ParseException;

/**
 * @author 黎勇炫
 * @date 2022年10月11日 18:09
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityGlobalFilter implements GlobalFilter, Ordered {

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 不是正确的的JWT不做解析处理
        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        if (StrUtil.isBlank(token) || !StrUtil.startWithIgnoreCase(token, SecurityConstants.JWT_PREFIX)) {
            return chain.filter(exchange);
        }
        // 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在则拦截访问
        token = StrUtil.replaceIgnoreCase(token, SecurityConstants.JWT_PREFIX, Strings.EMPTY);
        String payload = StrUtil.toString(JWSObject.parse(token).getPayload());
        request = exchange.getRequest().mutate()
                .header(SecurityConstants.JWT_PAYLOAD_KEY, URLEncoder.encode(payload, "UTF-8"))
                .build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(JWSObject.parse("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhbXMiLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoyMDI3OTgzNzAzLCJ1c2VySWQiOjQsImF1dGhvcml0aWVzIjpbIm1hbmFnZXIiLCJhZG1pbiJdLCJqdGkiOiI0MTM1ZDUyYS02NzUxLTRkYjAtYTZjZC04MmVlYzMwZjA0MzciLCJjbGllbnRfaWQiOiJhbXMiLCJ1c2VybmFtZSI6ImFtcyJ9.wfDCRHF21nbypFlwNkZm3hR1Zi_H5kKmFKmzKPfSSZOuS7FA3XWW_ZvLS9czAZ_tXhc1zlmnyp6RxdOtCgkwEmnO7sVhpUps0XF2YW5HIzS-C3ejy6HYaOoDYmfctDl5vuiTINcllTRk2Bh4JR8kUGGTKn1Gp_-unjaWP01tGks").getPayload());
    }

}