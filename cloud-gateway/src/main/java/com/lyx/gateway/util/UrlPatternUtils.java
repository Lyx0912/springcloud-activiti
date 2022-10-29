package com.lyx.gateway.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @author： 黎勇炫
 */
public class UrlPatternUtils {
    public static boolean match(String patternUrl, String requestUrl) {
        if (StrUtil.isBlank(patternUrl) || StrUtil.isBlank(requestUrl)) {
            return false;
        }
        PathMatcher matcher = new AntPathMatcher();
        return matcher.match(patternUrl, requestUrl);
    }

    public static void main(String[] args) {
        System.out.println(match("/a/b/c/**","/a/b/c/d/e"));
    }
}
