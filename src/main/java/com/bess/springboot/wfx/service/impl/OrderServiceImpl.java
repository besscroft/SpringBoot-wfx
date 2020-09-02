package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.OrderDAO;
import com.bess.springboot.wfx.pojo.Order;
import com.bess.springboot.wfx.service.OrderService;
import org.springframework.stereotype.Service;

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

//    @Override
//    public List<Order> getOrderByOrderType(String customerId, int start, int size, int orderType) {
//        return orderDAO.getOrderByOrderType(customerId, start, size, orderType);
//    }

    @Override
    public List<Order> getOrderByMemeber(String memeberId, int start, int size) {
        return orderDAO.getOrderByMemeber(memeberId, start, size);
    }
}
