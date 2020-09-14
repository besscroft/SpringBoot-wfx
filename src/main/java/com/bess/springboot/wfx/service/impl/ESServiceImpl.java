package com.bess.springboot.wfx.service.impl;

import com.bess.springboot.wfx.service.ESService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/12 9:58
 */
@Service
public class ESServiceImpl implements ESService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public SearchHits searchGood(String keyName) {
        SearchRequest searchRequest = new SearchRequest("wxbgood");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(keyName,"goodName","promoteDesc"));
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            return hits;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
