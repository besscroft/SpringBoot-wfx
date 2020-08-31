package com.bess.springboot.wfx.dao;

import com.bess.springboot.wfx.pojo.Customer;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 9:33
 */
public interface CustomerDAO {
    public Customer getCustomer(@Param("loginName") String loginName,
                                @Param("loginPwd") String loginPwd); // 根据账号和密码，获取Customer对象
    public Customer getCustomerByLoginName(String loginName);   // 登录后根据JWT解码后的用户名查询用户信息（Customer对象）
}
