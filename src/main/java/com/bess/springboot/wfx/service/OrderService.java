package com.bess.springboot.wfx.service;

import com.bess.springboot.wfx.pojo.Order;

import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/2 11:41
 */
public interface OrderService {
    public int getCount(String customerId,String memeberId);  // 根据id查询所有的订单信息的数量（动态SQL）
    public List<Order> listOrderByCustomerId(String customerId,int start,int size);   // 根据商户id查询所有的订单信息
    public List<Order> getOrderByMemeber(String memeberId,int start,int size);   // 根据自媒体用户id查询所有的订单信息
    public int getCountByState(String customerId,int state);  // 根据id和状态查询所有的订单信息的数量
    public List<Order> listOrderByState(String customerId,int state,int start,int size);   // 根据商户id和订单状态查询所有的订单信息
    public boolean updateOrderState(String orderId,int state); // 根据订单id和state修改订单状态
    public boolean insertOrder(Order order);    // 录单
    public String getOrderGoodNameByOrderIdAndMemeberId(String memeberId,String orderId);   // 根据自媒体用户id和订单id查询订单的商品名称
    public boolean updateIsFK(String orderId);   // 根据订单id修改订单的付款状态
}
