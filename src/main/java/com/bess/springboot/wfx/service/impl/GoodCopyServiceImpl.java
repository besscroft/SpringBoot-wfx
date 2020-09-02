package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.GoodCopyDAO;
import com.bess.springboot.wfx.dao.GoodDAO;
import com.bess.springboot.wfx.pojo.GoodCopy;
import com.bess.springboot.wfx.service.GoodCopyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/1 21:08
 */
@Service
public class GoodCopyServiceImpl implements GoodCopyService {

    @Resource
    private GoodCopyDAO goodCopyDAO;

    @Resource
    private GoodDAO goodDAO;

    @Override
    public int getCount(String customerId) {
        return goodCopyDAO.getCount(customerId);
    }

    @Override
    public List<GoodCopy> listGoodCopyByPage(String customerId, int start, int size) {
        return goodCopyDAO.listGoodCopyByPage(customerId,start,size);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean insertGoodCopy(GoodCopy goodCopy) {
        int i = goodCopyDAO.insertGoodCopy(goodCopy);
        if (i > 0) {
            return goodDAO.updateGoodCopy(goodCopy.getCopyId(), goodCopy.getCopyTitle(), goodCopy.getGoodsId()) > 0;
        }
        return false;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean deleteGoodCopy(int copyId) {
        return goodCopyDAO.deleteGoodCopy(copyId) > 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean updateGoodCopy(GoodCopy goodCopy) {
        return goodCopyDAO.updateGoodCopy(goodCopy) > 0;
    }
}
