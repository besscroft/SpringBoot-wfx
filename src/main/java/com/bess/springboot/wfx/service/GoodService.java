package com.bess.springboot.wfx.service;

import com.bess.springboot.wfx.pojo.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/31 15:02
 */
public interface GoodService {
    public int getCount(String customerId); // 查询所有商品数量
    public List<Good> listGoodByCustomerId(String customerId, int start, int size);   // 根据商户id查询所有的商品信息
    public boolean insertGood(Good good);   // 添加商品
    public boolean deleteGood(String goodId);   // 删除商品
    public boolean updateGood(Good good);   // 更新商品
    public List<Good> listGoodById(String customerId);   // 根据商户id查询所有的商品信息(不分页)
    public boolean updateGoodCopy(int copyIds,String copyDesc,String goodId);  // 添加文案后，对商品的文案关联信息更新
    public List<Good> listGood(int start,int size);   // 查询所有的商品信息
    public int getSellNum(String goodId);   // 根据商品id查询商品库存
    public boolean updateSellNum(String goodId,int num);    // 根据商品id修改库存
}
