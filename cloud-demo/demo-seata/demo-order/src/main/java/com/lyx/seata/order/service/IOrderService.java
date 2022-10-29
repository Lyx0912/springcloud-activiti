package com.lyx.seata.order.service;

import com.lyx.seata.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liyongxuan
 * @since 2022-10-13
 */
public interface IOrderService extends IService<Order> {

    void createOrder();
}
