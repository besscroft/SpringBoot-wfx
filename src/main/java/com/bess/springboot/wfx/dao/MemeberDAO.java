package com.bess.springboot.wfx.dao;

import com.bess.springboot.wfx.pojo.Customer;
import com.bess.springboot.wfx.pojo.Memeber;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 9:35
 */
public interface MemeberDAO {
    public Memeber getMemeber(@Param("account") String account,
                              @Param("password") String password);  // 根据账号和密码，获取Memeber对象
    public Memeber getMemeberByLoginName(String account);   // 登录后根据JWT解码后的用户名查询用户信息（Memeber对象）
}
