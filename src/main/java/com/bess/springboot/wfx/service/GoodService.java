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
    public boolean deleteGood(String goodId);   // 删除商品
    public boolean updateGood(Good good);   // 更新商品
    public List<Good> listGoodById(String customerId);   // 根据商户id查询所有的商品信息(不分页)
    public int updateGoodCopy(int copyIds,String copyDesc,String goodId);  // 添加文案后，对商品的文案关联信息更新
}
