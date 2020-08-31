package com.bess.springboot.wfx.service;

import com.bess.springboot.wfx.pojo.Memeber;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 10:34
 */
public interface MemeberService {
    public Memeber login(String account,String password);  // 自媒体用户登录
    public Memeber getMemeberByLoginName(String account);   // 登录后根据JWT解码后的用户名查询用户信息（Memeber对象）
}
