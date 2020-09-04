package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.OrderDAO;
import com.bess.springboot.wfx.pojo.Order;
import com.bess.springboot.wfx.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/2 11:42
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDAO orderDAO;

    @Override
    public int getCount(String customerId, String memeberId) {
        return orderDAO.getCount(customerId, memeberId);
    }

    @Override
    public List<Order> listOrderByCustomerId(String customerId, int start, int size) {
        return orderDAO.listOrderByCustomerId(customerId,start,size);
    }

    @Override
    public List<Order> getOrderByMemeber(String memeberId, int start, int size) {
        return orderDAO.getOrderByMemeber(memeberId, start, size);
    }

    @Override
    public int getCountByState(String customerId, int state) {
        return orderDAO.getCountByState(customerId, state);
    }

    @Override
    public List<Order> listOrderByState(String customerId, int state, int start, int size) {
        return orderDAO.listOrderByState(customerId, state, start, size);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean updateOrderState(String orderId, int state) {
        return orderDAO.updateOrderState(orderId,state) > 0;
    }

    @Override
    public boolean insertOrder(Order order) {
        return orderDAO.insertOrder(order) > 0;
    }
}
