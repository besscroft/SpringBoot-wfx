package com.bess.springboot.wfx.service;

import com.bess.springboot.wfx.pojo.Customer;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 10:36
 */
public interface CustomerService {
    public Customer login(String loginName,String loginPwd); // 商户登录
}
