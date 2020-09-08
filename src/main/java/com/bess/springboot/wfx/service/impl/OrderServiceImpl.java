package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.OrderDAO;
import com.bess.springboot.wfx.pojo.Order;
import com.bess.springboot.wfx.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public int getCount(String customerId, String memeberId) {
        int count = 0;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getCount").get("customerId-" + customerId + "-memeberId-" + memeberId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getCount").get("customerId-" + customerId + "-memeberId-" + memeberId);
                    if (s == null) {
                        count = orderDAO.getCount(customerId, memeberId);
                        String jsonStr = mapper.writeValueAsString(count);
                        stringRedisTemplate.boundHashOps("getCount").put("customerId-" + customerId + "-memeberId-" + memeberId,jsonStr);
                    }
                }
            } else {
                count = mapper.readValue(s, Integer.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Order> listOrderByCustomerId(String customerId, int start, int size) {
        List<Order> orders = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listOrderByCustomerId").get("orders-" + customerId + "-start-" + start + "-size-" + size);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listOrderByCustomerId").get("orders-" + customerId + "-start-" + start + "-size-" + size);
                    if (s == null) {
                        orders = orderDAO.listOrderByCustomerId(customerId, start, size);
                        String jsonStr = mapper.writeValueAsString(orders);
                        stringRedisTemplate.boundHashOps("listOrderByCustomerId").put("orders-" + customerId + "-start-" + start + "-size-" + size,jsonStr);
                    }
                }
            } else {
                orders = mapper.readValue(s, new TypeReference<List<Order>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getOrderByMemeber(String memeberId, int start, int size) {
        List<Order> orders = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getOrderByMemeber").get("orders-" + memeberId + "-start-" + start + "-size-" + size);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getOrderByMemeber").get("orders-" + memeberId + "-start-" + start + "-size-" + size);
                    if (s == null) {
                        orders = orderDAO.getOrderByMemeber(memeberId, start, size);
                        String jsonStr = mapper.writeValueAsString(orders);
                        stringRedisTemplate.boundHashOps("getOrderByMemeber").put("orders-" + memeberId + "-start-" + start + "-size-" + size,jsonStr);
                    }
                }
            } else {
                orders = mapper.readValue(s, new TypeReference<List<Order>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public int getCountByState(String customerId, int state) {
        int count = 0;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getCountByState").get("count-" + customerId + "-state-" + state);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getCountByState").get("count-" + customerId + "-state-" + state);
                    if (s == null) {
                        count = orderDAO.getCountByState(customerId, state);
                        String jsonStr = mapper.writeValueAsString(count);
                        stringRedisTemplate.boundHashOps("getCountByState").put("count-" + customerId + "-state-" + state,jsonStr);
                    }
                }
            } else {
                count = mapper.readValue(s, Integer.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Order> listOrderByState(String customerId, int state, int start, int size) {
        List<Order> orders = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listOrderByState").get("orders-" + customerId + "-state-" + state + "-start-" + start + "-size-" + size);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listOrderByState").get("orders-" + customerId + "-state-" + state + "-start-" + start + "-size-" + size);
                    if (s == null) {
                        orders = orderDAO.listOrderByState(customerId, state, start, size);
                        String jsonStr = mapper.writeValueAsString(orders);
                        stringRedisTemplate.boundHashOps("listOrderByState").put("orders-" + customerId + "-state-" + state + "-start-" + start + "-size-" + size,jsonStr);
                    }
                }
            } else {
                orders = mapper.readValue(s, new TypeReference<List<Order>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean updateOrderState(String orderId, int state) {
        int i = orderDAO.updateOrderState(orderId, state);
        if (i > 0) {
            stringRedisTemplate.delete("listOrderByCustomerId");
            stringRedisTemplate.delete("getOrderByMemeber");
            stringRedisTemplate.delete("getCountByState");
            stringRedisTemplate.delete("listOrderByState");
            stringRedisTemplate.delete("getOrderGoodNameByOrderIdAndMemeberId");
            return true;
        }
        return false;
    }

    @Override
    public boolean insertOrder(Order order) {
        int i = orderDAO.insertOrder(order);
        if (i > 0) {
            stringRedisTemplate.delete("getCount");
            stringRedisTemplate.delete("listOrderByCustomerId");
            stringRedisTemplate.delete("getOrderByMemeber");
            stringRedisTemplate.delete("getCountByState");
            stringRedisTemplate.delete("listOrderByState");
            stringRedisTemplate.delete("getOrderGoodNameByOrderIdAndMemeberId");
            return true;
        }
        return false;
    }

    @Override
    public String getOrderGoodNameByOrderIdAndMemeberId(String memeberId, String orderId) {
        String goodName = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getOrderGoodNameByOrderIdAndMemeberId").get("order-" + memeberId + "-orderId-" + orderId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getOrderGoodNameByOrderIdAndMemeberId").get("order-" + memeberId + "-orderId-" + orderId);
                    if (s == null) {
                        goodName = orderDAO.getOrderGoodNameByOrderIdAndMemeberId(memeberId, orderId);
                        String jsonStr = mapper.writeValueAsString(goodName);
                        stringRedisTemplate.boundHashOps("getOrderGoodNameByOrderIdAndMemeberId").put("order-" + memeberId + "-orderId-" + orderId,jsonStr);
                    }
                }
            } else {
                goodName = mapper.readValue(s, String.class);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return goodName;
    }

    @Override
    public boolean updateIsFK(String orderId) {
        int i = orderDAO.updateIsFK(orderId);
        if (i > 0) {
            stringRedisTemplate.delete("listOrderByCustomerId");
            stringRedisTemplate.delete("getOrderByMemeber");
            stringRedisTemplate.delete("getCountByState");
            stringRedisTemplate.delete("listOrderByState");
            stringRedisTemplate.delete("getOrderGoodNameByOrderIdAndMemeberId");
            return true;
        }
        return false;
    }
}
