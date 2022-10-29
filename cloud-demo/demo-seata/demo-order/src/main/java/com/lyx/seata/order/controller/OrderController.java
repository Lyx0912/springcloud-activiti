package com.lyx.seata.order.controller;


import com.lyx.common.base.result.R;
import com.lyx.seata.order.service.IOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liyongxuan
 * @since 2022-10-13
 */
@RestController
@RequestMapping("/order")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final IOrderService orderService;

    @RequestMapping("/create")
    public R createOrder(){
        log.info("订单服务开始创建订单");
        orderService.createOrder();
        return R.ok();
    }

}
