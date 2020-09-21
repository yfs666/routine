package com.yfs.es.train.estrain.service;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.yfs.es.train.estrain.entity.StockPrice;
import com.yfs.es.train.estrain.entity.ThsPrice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.TransportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StockPriceService {


    public static final String STOCK_PRICE_INDEX = "ths_stock_price";

    /**
     * 每次查询数据
     */
    private static final Integer BEFORE_DAYS = 284;


    @Autowired
    private RestHighLevelClient restHighLevelClient;


    /**
     * 对比和添加
     *
     * @param priceList 数据列表
     */
    public void compareAndAdd(List<ThsPrice> priceList) {
        if (CollectionUtils.isEmpty(priceList)) {
            return;
        }
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("date", priceList.get(0).getDate())).from(0).size(10000);
        List<String> existSymbols = this.queryFrom(searchSourceBuilder).stream().map(ThsPrice::getSymbol).collect(Collectors.toList());
        List<ThsPrice> needAddList = priceList.stream().filter(it -> !existSymbols.contains(it.getSymbol())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(needAddList)) {
            return;
        }
        this.batchAdd(needAddList);
    }

    /**
     * 批量增加
     */
    public void batchAdd(List<ThsPrice> priceList) {
        Gson gson = new Gson();
        BulkRequest bulkRequest = Requests.bulkRequest().timeout("60s");
        priceList.stream().map(gson::toJson).forEach(it -> bulkRequest.add(Requests.indexRequest(STOCK_PRICE_INDEX).source(it, XContentType.JSON)));
        try {
            BulkResponse bulkItemResponses = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("batchAdd error", e);
        }
    }


    /**
     * 查询
     * @param searchSourceBuilder 查询条件
     * @return 数据列表
     */
    public List<ThsPrice> queryFrom(SearchSourceBuilder searchSourceBuilder) {
        try {
            SearchRequest searchRequest = Requests.searchRequest(STOCK_PRICE_INDEX).source(searchSourceBuilder);
            CountRequest countRequest = new CountRequest();
            CountResponse count = restHighLevelClient.count(countRequest, RequestOptions.DEFAULT);
            System.out.println(count.getCount());
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits == null || hits.length == 0) {
                return Collections.emptyList();
            }
            return Arrays.stream(hits).map(hit -> {
                ThsPrice stockPrice = new Gson().fromJson(hit.getSourceAsString(), ThsPrice.class);
                stockPrice.setId(hit.getId());
                return stockPrice;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("queryFrom error, ", e);
        }
        return Collections.emptyList();
    }

    public void delete(ThsPrice thsPrice) {
        DeleteRequest deleteRequest = Requests.deleteRequest(STOCK_PRICE_INDEX);
        deleteRequest.id(thsPrice.getId());
        try {
            DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("delete result is {}", new Gson().toJson(delete.getResult()));
        } catch (Exception e) {
            log.error("delete error, ", e);
        }
    }


}
