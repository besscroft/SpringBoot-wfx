package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.MemeberDAO;
import com.bess.springboot.wfx.pojo.Memeber;
import com.bess.springboot.wfx.service.MemeberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Memeber login(String account, String password) {
        return memeberDAO.getMemeber(account,password);
    }

    @Override
    public Memeber getMemeberByLoginName(String account) {
        Memeber memeber = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getMemeberByLoginName").get("memeber-" + account);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getMemeberByLoginName").get("memeber-" + account);
                    if (s == null) {
                        memeber = memeberDAO.getMemeberByLoginName(account);
                        String jsonStr = mapper.writeValueAsString(memeber);
                        stringRedisTemplate.boundHashOps("getMemeberByLoginName").put("memeber-" + account,jsonStr);
                    }
                }
            } else {
                memeber = mapper.readValue(s, Memeber.class);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return memeber;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean insertMemeber(Memeber memeber) {
        return memeberDAO.insertMemeber(memeber) > 0;
    }
}
