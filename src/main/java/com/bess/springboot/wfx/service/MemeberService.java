package com.bess.springboot.wfx.service;

import com.bess.springboot.wfx.pojo.Memeber;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 10:34
 */
public interface MemeberService {
    public Memeber login(String account,String password);  // 自媒体用户登录
}
