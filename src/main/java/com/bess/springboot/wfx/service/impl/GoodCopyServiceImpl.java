package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.GoodCopyDAO;
import com.bess.springboot.wfx.dao.GoodDAO;
import com.bess.springboot.wfx.pojo.GoodCopy;
import com.bess.springboot.wfx.service.GoodCopyService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public int getCount(String customerId) {
        int count = 0;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getCount").get("count-" + customerId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getCount").get("count-" + customerId);
                    if (s == null) {
                        count = goodCopyDAO.getCount(customerId);
                        String jsonStr = mapper.writeValueAsString(count);
                        stringRedisTemplate.boundHashOps("getCount").put("count-" + customerId,jsonStr);
                    }
                }
            } else {
                count = mapper.readValue(s, Integer.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<GoodCopy> listGoodCopyByPage(String customerId, int start, int size) {
        List<GoodCopy> goodCopies = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listGoodCopyByPage").get("goodCopies-" + customerId + "-start-" + start + "-size-" + size);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listGoodCopyByPage").get("goodCopies-" + customerId + "-start-" + start + "-size-" + size);
                    if (s == null) {
                        goodCopies = goodCopyDAO.listGoodCopyByPage(customerId, start, size);
                        String jsonStr = mapper.writeValueAsString(goodCopies);
                        stringRedisTemplate.boundHashOps("listGoodCopyByPage").put("goodCopies-" + customerId + "-start-" + start + "-size-" + size,goodCopies);
                    }
                }
            } else {
                goodCopies = mapper.readValue(s, new TypeReference<List<GoodCopy>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goodCopies;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean insertGoodCopy(GoodCopy goodCopy) {
        int i = goodCopyDAO.insertGoodCopy(goodCopy);
        if (i > 0) {
            int j = goodDAO.updateGoodCopy(goodCopy.getCopyId(), goodCopy.getCopyTitle(), goodCopy.getGoodsId());
            if (j > 0) {
                stringRedisTemplate.delete("getCount");
                stringRedisTemplate.delete("listGoodCopyByPage");
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean deleteGoodCopy(int copyId) {
        int i = goodCopyDAO.deleteGoodCopy(copyId);
        if (i > 0) {
            stringRedisTemplate.delete("getCount");
            stringRedisTemplate.delete("listGoodCopyByPage");
            return true;
        }
        return false;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean updateGoodCopy(GoodCopy goodCopy) {
        int i = goodCopyDAO.updateGoodCopy(goodCopy);
        if (i > 0) {
            stringRedisTemplate.delete("getCount");
            return true;
        }
        return false;
    }
}
