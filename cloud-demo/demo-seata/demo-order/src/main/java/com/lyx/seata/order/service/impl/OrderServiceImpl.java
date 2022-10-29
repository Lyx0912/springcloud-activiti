package com.lyx.seata.order.service.impl;

import com.lyx.seata.order.clients.AccountFeignService;
import com.lyx.seata.order.clients.StorageFeignService;
import com.lyx.seata.order.entity.Order;
import com.lyx.seata.order.mapper.OrderMapper;
import com.lyx.seata.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyongxuan
 * @since 2022-10-13
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final AccountFeignService accountFeignService;
    private final StorageFeignService storageFeignService;

    @Override
    @GlobalTransactional
    public void createOrder() {
        Order order = Order.builder()
                .count(10)
                .money(100)
                .productId(1L)
                .status(0)
                .userId(1L)
                .build();

        save(order);
        // 通过feign调用账户服务
        accountFeignService.decrease(order.getUserId(),order.getMoney());
        // 通过feign调用库存服务
        storageFeignService.decrease(order.getProductId(),order.getCount());
        order.setStatus(1);
        updateById(order);
    }
}
