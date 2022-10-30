package com.lyx.common.web.utils;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.lyx.common.base.constant.SecurityConstants;

import com.nimbusds.jose.JWSObject;
import net.minidev.json.JSONObject;
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
        // 截取掉前缀
        token = token.replace(SecurityConstants.JWT_PREFIX,"");
        if(!StringUtils.isEmpty(token)){
            try {
                // 解析token
                JSONObject jsonObject = JWSObject.parse(token).getPayload().toJSONObject();
                if(!Objects.isNull(jsonObject)){
                    return (Long) jsonObject.get("userId");
                }
            } catch (Exception e) {
                e.printStackTrace();
//                throw new BizException(ResultCode.TOKEN_INVALID_OR_EXPIRED);
            }
        }
        return null;
    }
}
