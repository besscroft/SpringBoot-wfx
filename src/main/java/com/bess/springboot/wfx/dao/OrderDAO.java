package com.bess.springboot.wfx.dao;

import com.bess.springboot.wfx.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/2 10:40
 */
public interface OrderDAO {
    public int getCount(@Param("customerId") String customerId,
                        @Param("memeberId") String memeberId);  // 根据id查询所有的订单信息的数量（动态SQL）
    public List<Order> listOrderByCustomerId(@Param("customerId") String customerId,
                                             @Param("start") int start,
                                             @Param("size") int size);   // 根据商户id查询所有的订单信息
    public List<Order> getOrderByMemeber(@Param("memeberId") String memeberId,
                                         @Param("start") int start,
                                         @Param("size") int size);   // 根据自媒体用户id查询所有的订单信息
    public int getCountByState(@Param("customerId") String customerId,
                               @Param("state") int state);  // 根据id和状态查询所有的订单信息的数量
    public List<Order> listOrderByState(@Param("customerId") String customerId,
                                        @Param("state") int state,
                                        @Param("start") int start,
                                        @Param("size") int size);   // 根据商户id和订单状态查询所有的订单信息
    public int updateOrderState(@Param("orderId") String orderId,
                                @Param("state") int state); // 根据订单id和state修改订单状态
    public int insertOrder(Order order);    // 录单
    public String getOrderGoodNameByOrderIdAndMemeberId(@Param("memeberId") String memeberId,
                                               @Param("orderId") String orderId);   // 根据自媒体用户id和订单id查询订单的商品名称
    public int updateIsFK(String orderId);   // 根据订单id修改订单的付款状态
}
