package com.bess.springboot.wfx.dao;

import com.bess.springboot.wfx.pojo.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/31 14:25
 */
public interface GoodDAO {
    public List<Good> listGoodByCustomerId(@Param("customerId") String customerId,@Param("start") int start,@Param("size") int size);   // 根据商户id查询所有的商品信息
    public int insertGood(Good good);   // 添加商品
    public int deleteGood(String goodId);   // 删除商品
    public int updateGood(Good good);   // 更新商品
}
