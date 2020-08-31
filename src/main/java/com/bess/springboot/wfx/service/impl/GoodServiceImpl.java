package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.GoodDAO;
import com.bess.springboot.wfx.pojo.Good;
import com.bess.springboot.wfx.service.GoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/31 15:02
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Resource
    private GoodDAO goodDAO;

    @Override
    public List<Good> listGoodByCustomerId(String customerId, int start, int size) {
        return goodDAO.listGoodByCustomerId(customerId,start,size);
    }

    @Override
    public boolean insertGood(Good good) {
        return goodDAO.insertGood(good) > 0;
    }

    @Override
    public boolean deleteGood(String goodId) {
        return goodDAO.deleteGood(goodId) > 0;
    }

    @Override
    public boolean updateGood(Good good) {
        return goodDAO.updateGood(good) > 0;
    }
}
