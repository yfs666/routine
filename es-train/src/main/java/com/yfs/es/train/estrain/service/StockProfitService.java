package com.yfs.es.train.estrain.service;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.yfs.es.train.estrain.entity.StockInfo;
import com.yfs.es.train.estrain.entity.StockPrice;
import com.yfs.es.train.estrain.entity.StockProfit;
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
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StockProfitService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private StockInfoService stockInfoService;

    @Autowired
    private StockPriceService stockPriceService;

    Gson gson = new Gson();

    public static BigDecimal YI = new BigDecimal("100000000");

    private static final String STOCK_PROFIT_INDEX = "ths_stock_profit";

    private static final BigDecimal compare = BigDecimal.valueOf(-0.01);
    private static final BigDecimal compare2020 = BigDecimal.valueOf(0.1);

    public  List<StockProfit> findUpList() {

        SearchSourceBuilder searchSourceBuilder1 = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.boolQuery()
                        .filter(QueryBuilders.termQuery("date", "2021-02-18"))
                )
                .from(0).size(10000);
        Map<String, ThsPrice> priceMap = stockPriceService.queryFrom(searchSourceBuilder1).stream().collect(Collectors.toMap(ThsPrice::getSymbol, Function.identity(), (o1, o2) -> o1));
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("reportName.keyword", "2020三季报")).from(0).size(10000);

        List<StockProfit> stockProfits = this.queryFrom(searchSourceBuilder).stream()
                .filter(it -> it.getProfit().compareTo(YI) > 0)
                .filter(it -> !"银行".equals(Optional.ofNullable(priceMap.get(it.getSymbol())).map(ThsPrice::getIndustry).orElse("")))
                .peek(it -> {
                    BigDecimal value = Optional.ofNullable(priceMap.get(it.getSymbol())).map(ThsPrice::getMarketValue).orElse(YI);
                    it.setRevenuePercent(it.getProfit().divide(value, 2, BigDecimal.ROUND_HALF_UP));
                }).sorted(Comparator.comparing(StockProfit::getRevenuePercent).reversed()).collect(Collectors.toList());
        int index = 0;
        for (StockProfit stockProfit : stockProfits) {
            index++;
            System.out.println(index + "-" + stockProfit.getRevenuePercent());
        }
        return stockProfits;
    }


    /**
     * 查询
     *
     * @param searchSourceBuilder 查询条件
     * @return 数据列表
     */
    public List<StockProfit> queryFrom(SearchSourceBuilder searchSourceBuilder) {
        try {
            SearchRequest searchRequest = Requests.searchRequest(STOCK_PROFIT_INDEX).source(searchSourceBuilder);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits == null || hits.length == 0) {
                return Collections.emptyList();
            }
            return Arrays.stream(hits).map(hit -> {
                StockProfit stockProfit = new Gson().fromJson(hit.getSourceAsString(), StockProfit.class);
                stockProfit.setId(hit.getId());
                return stockProfit;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("queryFrom error, ", e);
        }
        return Collections.emptyList();
    }


}
