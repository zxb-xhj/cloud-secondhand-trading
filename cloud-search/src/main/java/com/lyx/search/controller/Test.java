package com.lyx.search.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @Author: xhj
 * @Date: 2024/02/16/22:34
 * @Description:
 */
public class Test {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        //构建es HttpHost对象
        HttpHost httpHost1 = new HttpHost("127.0.0.1", 9200, "http");

        // 滚动时间窗口
        long scrollTime = 1L;
        // 每次返回的文档数量
        int batchSize = 2;
        //索引名
        String indexName = "goods";

        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHost1))) {

            //构建查询请求
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.boolQuery());
            searchSourceBuilder.size(batchSize);
            //设置查询返回字段
            String[] includes = {};
            searchSourceBuilder.fetchSource(includes, null);

            // 滚动查询请求
            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.source(searchSourceBuilder);
            //设置请求滚动时间窗口时间
            searchRequest.scroll(TimeValue.timeValueMinutes(scrollTime));

            //执行首次检索
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            //首次检索返回scrollId，用于下一次的滚动查询
            String scrollId = searchResponse.getScrollId();
            //获取首次检索命中结果
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            //计数
            int count = 0;
            // 处理第一批结果
            for (SearchHit hit : searchHits) {
                // 处理单个文档
                JSONObject dataJson = (JSONObject) JSON.parse(hit.getSourceAsString());
                System.out.println("====对首次请求的进行处理，当前计数：" + count++);
                System.out.println("====dataJson1：" + dataJson);
            }

            // 处理滚动结果
            while (searchHits != null && searchHits.length > 0) {
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(TimeValue.timeValueMinutes(scrollTime));

                searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
                scrollId = searchResponse.getScrollId();
                searchHits = searchResponse.getHits().getHits();

                for (SearchHit hit : searchHits) {
                    JSONObject dataJson = (JSONObject) JSON.parse(hit.getSourceAsString());
                    System.out.println("====滚动查询，当前计数：" + count++);
                    System.out.println("====dataJson2：" + dataJson);
                }
            }

            // 清理滚动上下文
            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.addScrollId(scrollId);

            ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
            boolean succeeded = clearScrollResponse.isSucceeded();

            long end = System.currentTimeMillis();
            System.out.println("共执行时间：" + (end - start) / 1000 + " s");

        } catch (Exception e) {
            System.out.println("===error==" + e.getMessage());
            e.printStackTrace();
        }
    }

}
