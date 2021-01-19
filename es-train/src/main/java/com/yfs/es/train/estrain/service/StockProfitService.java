package com.yfs.es.train.estrain.service;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.yfs.es.train.estrain.entity.StockInfo;
import com.yfs.es.train.estrain.entity.StockProfit;
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

    Gson gson = new Gson();

    private static final String STOCK_PROFIT_INDEX = "ths_stock_profit";

    private static final BigDecimal compare = BigDecimal.valueOf(-0.01);
    private static final BigDecimal compare2020 = BigDecimal.valueOf(0.1);

    public void findUpList() {
        List<StockInfo> stockInfos = stockInfoService.pageList(0, 10000);
        AtomicInteger count = new AtomicInteger();
        List<Tuple2<String, BigDecimal>> upDetails = stockInfos.parallelStream().filter(it -> !it.getCode().startsWith("688")).map(stockInfo -> {
            SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("symbol.keyword", stockInfo.getSymbol())).from(0).size(10000);
            List<StockProfit> stockProfits = this.queryFrom(searchSourceBuilder);
            Map<String, StockProfit> stockProfitMap = stockProfits.stream().collect(Collectors.toMap(StockProfit::getReportName, Function.identity(), (o1, o2) -> o1));
            BigDecimal profit2017 = Optional.ofNullable(stockProfitMap.get("2017年报")).map(StockProfit::getProfitPercent).orElse(null);
            BigDecimal profit2018 = Optional.ofNullable(stockProfitMap.get("2018年报")).map(StockProfit::getProfitPercent).orElse(null);
            BigDecimal profit2019 = Optional.ofNullable(stockProfitMap.get("2019年报")).map(StockProfit::getProfitPercent).orElse(null);
            BigDecimal profit2020 = Optional.ofNullable(stockProfitMap.get("2020三季报")).map(StockProfit::getProfitPercent).orElse(null);
            if (profit2017 == null || profit2018 == null || profit2019 == null || profit2020 == null) {
                return Tuples.of(stockInfo.getCode(), BigDecimal.ZERO);
            }
            if (profit2017.compareTo(compare) < 0 || profit2018.compareTo(compare) < 0 || profit2019.compareTo(compare) < 0 || profit2020.compareTo(compare2020) < 0) {
                return Tuples.of(stockInfo.getCode(), BigDecimal.ZERO);
            }
            System.out.println(stockInfo.getCode() + " " + count.getAndIncrement());
            return Tuples.of(stockInfo.getCode(), profit2020);
        }).filter(it -> it.getT2().compareTo(BigDecimal.ZERO) > 0).sorted(Comparator.<Tuple2<String, BigDecimal>, BigDecimal>comparing(Tuple2::getT2).reversed()).collect(Collectors.toList());
        String upList = upDetails.stream().map(Tuple2::getT1).collect(Collectors.joining(" "));
        System.out.println(upList);
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
