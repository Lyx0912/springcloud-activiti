package com.lyx.activiti.controller;

import com.lyx.common.base.result.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 黎勇炫
 * @date 2022年10月26日 22:17
 */
@RestController
public class ActivitiController {

    @RequestMapping("/testGitee")
    public R test(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return R.ok();
    }
}
