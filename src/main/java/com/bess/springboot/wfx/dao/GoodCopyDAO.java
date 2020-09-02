package com.bess.springboot.wfx.dao;

import com.bess.springboot.wfx.pojo.GoodCopy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/1 20:16
 */
public interface GoodCopyDAO {
    public List<GoodCopy> listGoodCopyByPage(@Param("customerId") String customerId, @Param("start") int start, @Param("size") int size);   // 根据商户id查询所有的商品文案信息
    public int insertGoodCopy(GoodCopy goodCopy);   // 添加商品文案
    public int deleteGoodCopy(int copyId);   // 删除商品文案
    public int updateGoodCopy(GoodCopy goodCopy);   // 更新商品文案
    public int getCount(String customerId); // 根据商户id查询所有的商品文案信息的数量
}
