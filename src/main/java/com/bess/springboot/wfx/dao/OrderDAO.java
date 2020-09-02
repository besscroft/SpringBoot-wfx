package com.bess.springboot.wfx.dao;

import com.bess.springboot.wfx.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/2 10:40
 */
public interface OrderDAO {
    public List<Order> listOrderByCustomerId(@Param("customerId") String customerId,
                                             @Param("start") int start,
                                             @Param("size") int size);   // 根据商户id查询所有的订单信息
    public List<Order> getOrderByOrderType(@Param("customerId") String customerId,
                                        @Param("start") int start,
                                        @Param("size") int size,
                                        @Param("orderType") int orderType);   // 根据商户id查询所有的订单信息(orderType字段)
}
