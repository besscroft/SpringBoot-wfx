package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.dao.GoodDAO;
import com.bess.springboot.wfx.pojo.Good;
import com.bess.springboot.wfx.service.GoodService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
 * @DateTime 2020/8/31 15:02
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Resource
    private GoodDAO goodDAO;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public int getCount(String customerId) {
        int count = 0;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("GoodServiceImplCount").get("count-"+customerId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("GoodServiceImplCount").get("count-"+customerId);
                    if (s == null) {
                        count = goodDAO.getCount(customerId);
                        String jsonStr = mapper.writeValueAsString(count);
                        stringRedisTemplate.boundHashOps("GoodServiceImplCount").put("count-"+customerId,jsonStr);
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
    public List<Good> listGoodByCustomerId(String customerId, int start, int size) {
        List<Good> goods = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listGoodByCustomerId").get("goods-" + customerId + "-start-" + start + "-size-" + size);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listGoodByCustomerId").get("goods-" + customerId + "-start-" + start + "-size-" + size);
                    if (s == null) {
                        // 如果Redis中没有数据，则查询数据库
                        goods = goodDAO.listGoodByCustomerId(customerId, start, size);
                        // 将list集合转成字符串
                        String jsonStr = mapper.writeValueAsString(goods);
                        stringRedisTemplate.boundHashOps("listGoodByCustomerId").put("goods-" + customerId + "-start-" + start + "-size-" + size,jsonStr);
                    }
                }
            } else {
                // 如果从redis中查出数据了，将json字符串转换成List集合
                goods = mapper.readValue(s, new TypeReference<List<Good>>() {
                });
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean insertGood(Good good) {
        int i = goodDAO.insertGood(good);
        if (i > 0) {
            stringRedisTemplate.delete("GoodServiceImplCount");
            stringRedisTemplate.delete("listGoodByCustomerId");
            stringRedisTemplate.delete("listGoodById");
            stringRedisTemplate.delete("listGood");
            return true;
        }
        return false;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean deleteGood(String goodId) {
        int i = goodDAO.deleteGood(goodId);
        if (i > 0) {
            stringRedisTemplate.delete("GoodServiceImplCount");
            stringRedisTemplate.delete("listGoodByCustomerId");
            stringRedisTemplate.delete("listGoodById");
            stringRedisTemplate.delete("listGood");
            return true;
        }
        return false;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean updateGood(Good good) {
        int i = goodDAO.updateGood(good);
        if (i > 0) {
            System.out.println("数据库更新成功");
            stringRedisTemplate.delete("listGoodByCustomerId");
            stringRedisTemplate.delete("listGoodById");
            stringRedisTemplate.delete("listGood");
            System.out.println("Redis更新成功");
            return true;
        }
        return false;
    }

    @Override
    public List<Good> listGoodById(String customerId) {
        List<Good> goods = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listGoodById").get("customerId-" + customerId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listGoodById").get("customerId-" + customerId);
                    if (s == null) {
                        goods = goodDAO.listGoodById(customerId);
                        String jsonStr = mapper.writeValueAsString(goods);
                        stringRedisTemplate.boundHashOps("listGoodById").put("customerId-"+customerId,jsonStr);
                    }
                }
            } else {
                goods = mapper.readValue(s, new TypeReference<List<Good>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean updateGoodCopy(int copyIds, String copyDesc, String goodId) {
        int i = goodDAO.updateGoodCopy(copyIds, copyDesc, goodId);
        if (i > 0) {
            stringRedisTemplate.delete("listGoodByCustomerId");
            stringRedisTemplate.delete("listGoodById");
            stringRedisTemplate.delete("listGood");
            return true;
        }
        return false;
    }

    @Override
    public List<Good> listGood(int start, int size) {
        List<Good> goods = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listGood").get("start-" + start + "-size-" + size);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listGood").get("start-" + start + "-size-" + size);
                    if (s == null) {
                        goods = goodDAO.listGood(start, size);
                        String jsonStr = mapper.writeValueAsString(goods);
                        stringRedisTemplate.boundHashOps("listGood").put("start-" + start + "-size-" + size,jsonStr);
                    }
                }
            } else {
                goods = mapper.readValue(s, new TypeReference<List<Good>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    }
}
