package com.bess.springboot.wfx.service;

import com.bess.springboot.wfx.pojo.GoodCopy;

import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/1 21:06
 */
public interface GoodCopyService {
    public int getCount(String customerId); // 根据商户id查询所有的商品文案信息的数量
    public List<GoodCopy> listGoodCopyByPage(String customerId,int start,int size);   // 根据商户id查询所有的商品文案信息
    public boolean insertGoodCopy(GoodCopy goodCopy);   // 添加商品文案
    public boolean deleteGoodCopy(int copyId);   // 删除商品文案
    public boolean updateGoodCopy(GoodCopy goodCopy);   // 更新商品文案
}
