package com.lyx.common.web.utils;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.lyx.common.base.constant.SecurityConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;

/**
 * @author 黎勇炫
 * @date 2022年10月29日 12:01
 */
public class UserContext {

    public static Long getCurrentUserId(){
        // 获取servlet请求对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 获取请求中的token
        String token = requestAttributes.getRequest().getHeader(SecurityConstants.AUTHORIZATION_KEY);
        if(!StringUtils.isEmpty(token)){
            try {
                // 解析token
                JSONObject jsonObject = (JSONObject) JSONObject.parse(URLDecoder.decode(token,"utf-8"));
                if(!Objects.isNull(jsonObject)){
                    jsonObject.getLong("userId");
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
