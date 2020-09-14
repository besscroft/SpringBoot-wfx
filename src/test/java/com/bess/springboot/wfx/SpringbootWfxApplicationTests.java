package com.bess.springboot.wfx;

import com.bess.springboot.wfx.pojo.Good;
import com.bess.springboot.wfx.service.GoodService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootWfxApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private GoodService goodService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreateIndex() throws IOException {
        List<Good> goods = goodService.listGood(0, 10);
        System.out.println(goods);
        IndexRequest request = new IndexRequest("wxbgood");
        for (Good good:goods) {
            String jsonStr = mapper.writeValueAsString(good);
            request.id(good.getGoodId());
            request.source(jsonStr, XContentType.JSON);
            IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            System.out.println(indexResponse);
        }
    }

    @Test
    public void testSearchIndex() throws IOException {
        SearchRequest searchRequest = new SearchRequest("wxbgood");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        String key = "java";    // 查询的输入值
        searchSourceBuilder.query(QueryBuilders.matchQuery("goodName",key));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        String jsonStr = mapper.writeValueAsString(hits);
        System.out.println(jsonStr);
//        for (SearchHit hit : hits){
//
//        }
    }
}
