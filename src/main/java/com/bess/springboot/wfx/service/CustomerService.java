package com.bess.springboot.wfx.service;

import com.bess.springboot.wfx.pojo.Customer;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 10:36
 */
public interface CustomerService {
    public Customer login(String loginName,String loginPwd); // 商户登录
    public Customer getCustomerByLoginName(String loginName);   // 登录后根据JWT解码后的用户名查询用户信息（Customer对象）
}
