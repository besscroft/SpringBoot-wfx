package com.bess.springboot.wfx.service;

import org.elasticsearch.search.SearchHits;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/12 9:58
 */
public interface ESService {
    public SearchHits searchGood(String keyName);   // 根据关键词去es查询商品信息
}
