package com.bess.springboot.wfx.service;

import com.bess.springboot.wfx.pojo.Order;

import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/2 11:41
 */
public interface OrderService {
    public List<Order> listOrderByCustomerId(String customerId,int start,int size);   // 根据商户id查询所有的订单信息
    public List<Order> getOrderByOrderType(String customerId,int start,int size,int orderType);   // 根据商户id查询所有的订单信息(orderType字段)
}
