package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.MemeberDAO;
import com.bess.springboot.wfx.pojo.Memeber;
import com.bess.springboot.wfx.service.MemeberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 10:35
 */
@Service
public class MemeberServiceImpl implements MemeberService {

    @Resource
    private MemeberDAO memeberDAO;

    @Override
    public Memeber login(String account, String password) {
        return memeberDAO.getMemeber(account,password);
    }

    @Override
    public Memeber getMemeberByLoginName(String account) {
        return memeberDAO.getMemeberByLoginName(account);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean insertMemeber(Memeber memeber) {
        return memeberDAO.insertMemeber(memeber) > 0;
    }
}
