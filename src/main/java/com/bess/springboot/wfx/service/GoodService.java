package com.bess.springboot.wfx.service;

import com.bess.springboot.wfx.pojo.Good;

import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/31 15:02
 */
public interface GoodService {
    public List<Good> listGoodByCustomerId(String customerId, int start, int size);   // 根据商户id查询所有的商品信息
    public boolean insertGood(Good good);   // 添加商品
}
