package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.CustomerDAO;
import com.bess.springboot.wfx.pojo.Customer;
import com.bess.springboot.wfx.service.CustomerService;
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

    @Override
    public Customer login(String loginName, String loginPwd) {
        return customerDAO.getCustomer(loginName,loginPwd);
    }

    @Override
    public Customer getCustomerByLoginName(String loginName) {
        return customerDAO.getCustomerByLoginName(loginName);
    }
}
