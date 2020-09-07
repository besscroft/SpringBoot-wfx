package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.CustomerDAO;
import com.bess.springboot.wfx.pojo.Customer;
import com.bess.springboot.wfx.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 10:37
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDAO customerDAO;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Customer login(String loginName, String loginPwd) {
        return customerDAO.getCustomer(loginName,loginPwd);
    }

    @Override
    public Customer getCustomerByLoginName(String loginName) {
        Customer customer = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getCustomerByLoginName").get("customer-" + loginName);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getCustomerByLoginName").get("customer-" + loginName);
                    if (s == null) {
                        customer = customerDAO.getCustomerByLoginName(loginName);
                        String jsonStr = mapper.writeValueAsString(customer);
                        stringRedisTemplate.boundHashOps("getCustomerByLoginName").put("customer-"+loginName,jsonStr);
                    }
                }
            } else {
                customer = mapper.readValue(s, Customer.class);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
