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
}
