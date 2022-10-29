package com.lyx.admin.ser.controller;

import com.lyx.common.base.result.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黎勇炫
 * @date 2022年10月11日 19:54
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping
    public R test(){
        return R.ok("chenggong");
    }
}
