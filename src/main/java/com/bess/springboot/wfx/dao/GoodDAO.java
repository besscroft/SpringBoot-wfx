package com.bess.springboot.wfx.dao;

import com.bess.springboot.wfx.pojo.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/31 14:25
 */
public interface GoodDAO {
    public List<Good> listGoodByCustomerId(@Param("customerId") String customerId,
                                           @Param("start") int start,
                                           @Param("size") int size);   // 根据商户id查询所有的商品信息
    public int insertGood(Good good);   // 添加商品
    public int deleteGood(String goodId);   // 删除商品
    public int updateGood(Good good);   // 更新商品
    public List<Good> listGoodById(String customerId);   // 根据商户id查询所有的商品信息(不分页)
    public int updateGoodCopy(@Param("copyIds") int copyIds,
                              @Param("copyDesc") String copyDesc,
                              @Param("goodId") String goodId);  // 添加文案后，对商品的文案关联信息更新
}
