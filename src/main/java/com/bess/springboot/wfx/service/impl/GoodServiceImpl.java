package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.GoodDAO;
import com.bess.springboot.wfx.pojo.Good;
import com.bess.springboot.wfx.service.GoodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public int getCount(String customerId) {
        return goodDAO.getCount(customerId);
    }

    @Override
    public List<Good> listGoodByCustomerId(String customerId, int start, int size) {
        return goodDAO.listGoodByCustomerId(customerId,start,size);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean insertGood(Good good) {
        return goodDAO.insertGood(good) > 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean deleteGood(String goodId) {
        return goodDAO.deleteGood(goodId) > 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean updateGood(Good good) {
        return goodDAO.updateGood(good) > 0;
    }

    @Override
    public List<Good> listGoodById(String customerId) {
        return goodDAO.listGoodById(customerId);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public int updateGoodCopy(int copyIds, String copyDesc, String goodId) {
        return goodDAO.updateGoodCopy(copyIds,copyDesc,goodId);
    }

    @Override
    public List<Good> listGood(int start, int size) {
        return goodDAO.listGood(start,size);
    }
}
