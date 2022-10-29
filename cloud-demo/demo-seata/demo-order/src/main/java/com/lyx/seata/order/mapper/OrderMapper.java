package com.lyx.seata.order.mapper;

import com.lyx.seata.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongxuan
 * @since 2022-10-13
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
