package com.yfs.es.train.estrain.service;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.yfs.es.train.estrain.entity.StockInfo;
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
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
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
import java.util.*;
import java.util.concurrent.TimeUnit;
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


    public List<ThsPrice> queryBy(String code, Integer from, Integer size, Long startTime, Long endTime, SortOrder sortOrder) {
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(
                        QueryBuilders.boolQuery()
                                .filter(QueryBuilders.termQuery("code", code))
                                .filter(QueryBuilders.rangeQuery("dayTime").lte(endTime).gte(startTime))
                )
                .from(from)
                .size(size)
                .sort("dayTime", sortOrder);
        return this.queryFrom(searchSourceBuilder);
    }


    /**
     * 查询
     * @param searchSourceBuilder 查询条件
     * @return 数据列表
     */
    public List<ThsPrice> queryFrom(SearchSourceBuilder searchSourceBuilder) {
        try {
            SearchRequest searchRequest = Requests.searchRequest(STOCK_PRICE_INDEX).source(searchSourceBuilder);
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


    public String highBefore(StockInfo stockInfo, long startTime, long endTime) {
        try {
            return this.highBefore(stockInfo.getCode(), startTime, endTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockInfo.getCode();
    }

    public String highBefore(String code, long startTime, long endTime) throws IOException {
        int from = 0;
        int size = 500;
//        while (true) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            List<ThsPrice> stockPrices = this.queryBy(code, from, size, startTime, endTime, SortOrder.DESC);
//            List<StockPrice> stockPrices = this.testQuery(code, from, size, 1546272000000L, 1595169096845L, SortOrder.DESC);
            if (CollectionUtils.isEmpty(stockPrices)) {
//                break;
                return code;
            }
            from = from + size;
            stockPrices = stockPrices.stream().filter(it -> it.getHighBefore() == null).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(stockPrices)) {
                return code;
//                continue;
            }
            BulkRequest bulkRequest = Requests.bulkRequest();
            for (ThsPrice stockPrice : stockPrices) {
                SearchRequest searchRequest = Requests.searchRequest(STOCK_PRICE_INDEX);
                searchRequest.source()
                        .query(
                                QueryBuilders.boolQuery()
                                        .filter(QueryBuilders.termQuery("code", code))
                                        .filter(QueryBuilders.rangeQuery("dayTime").lt(stockPrice.getDayTime()).gt(0))
                                        .filter(QueryBuilders.rangeQuery("high").gte(stockPrice.getHigh()))
                        )
                        .from(0)
                        .size(1)
                        .sort("dayTime", SortOrder.DESC);
                SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                Integer highBeforeDays = searchResponse.getHits().getTotalHits().value == 0L ? 1000 :
                        Arrays.stream(searchResponse.getHits().getHits()).map(documentFields -> {
                            ThsPrice stockPriceDetail = new Gson().fromJson(documentFields.getSourceAsString(), ThsPrice.class);
                            stockPriceDetail.setId(documentFields.getId());
                            return stockPriceDetail;
                        }).findFirst().map(before -> (stockPrice.getDayTime() - before.getDayTime()) * 5 / 1000 / 60 / 60 / 24 / 7).orElse(1000L).intValue();
                stockPrice.setHighBefore(highBeforeDays);
                bulkRequest.add(new UpdateRequest(STOCK_PRICE_INDEX, stockPrice.getId()).doc(new Gson().toJson(stockPrice), XContentType.JSON));
            }
            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            log.info(String.format("highBefore process 500, cost %s ms, has failure %s", stopwatch.elapsed(TimeUnit.MILLISECONDS), bulkResponse.hasFailures()));
//        }
        return code;
    }



    /**
     * 计算指标
     */
    public void correctData(String code, long startTime, long endTime) throws IOException {
        int from = 0;
        int size = 2;
//        while (true) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            List<ThsPrice> stockPrices = this.queryBy(code, from, size, startTime, endTime, SortOrder.DESC);
            if (CollectionUtils.isEmpty(stockPrices)) {
//                break;
                return;
            }
//            from = from + size;
            stockPrices = stockPrices.stream()/*.filter(it -> it.getMa5() == null || it.getMa60() == null)*/.collect(Collectors.toList());
            if (CollectionUtils.isEmpty(stockPrices)) {
                return;
//                continue;
            }
            BulkRequest bulkRequest = Requests.bulkRequest();
//            Flux.fromIterable(stockPrices)
//                    .flatMap(stockPrice -> Mono.fromSupplier(() -> this.wrapUpdateRequest(stockPrice, ma)).subscribeOn(Schedulers.elastic()))
//                    .filter(Objects::nonNull)
//                    .doOnError(e -> System.out.println("error" + e))
//                    .doOnNext(bulkRequest::add)
//                    .collectList()
//                    .block();
            stockPrices.stream().map(this::wrapUpdateRequest).forEach(bulkRequest::add);
            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            log.info(String.format("correctData process 500, cost %s ms, has failure %s", stopwatch.elapsed(TimeUnit.MILLISECONDS), bulkResponse.hasFailures()));
//        }
    }


    private UpdateRequest wrapUpdateRequest(ThsPrice stockPrice) {
        try {
            List<Integer> maList = Lists.newArrayList(5, 10, 20, 30, 60);
            List<ThsPrice> maPrices = this.queryBy(stockPrice.getCode(), 0, 80, 0L, stockPrice.getDayTime(), SortOrder.DESC);
            for (Integer ma : maList) {
                BigDecimal maNPrice = stockPrice.getClose();
                if (!CollectionUtils.isEmpty(maPrices)) {
                    BigDecimal sum = BigDecimal.ZERO;
                    int count = ma <= maPrices.size() ? ma : maPrices.size();
                    for (int i = 0; i < count; i++) {
                        ThsPrice maPrice = maPrices.get(i);
                        sum = sum.add(maPrice.getClose());
                    }
                    maNPrice = sum.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);
                }
                switch (ma) {
                    case 5:
                        stockPrice.setMa5(maNPrice);
                    case 10:
                        stockPrice.setMa10(maNPrice);
                    case 20:
                        stockPrice.setMa20(maNPrice);
                    case 30:
                        stockPrice.setMa30(maNPrice);
                    case 60:
                        stockPrice.setMa60(maNPrice);
                    default:
                }
            }
            return new UpdateRequest(STOCK_PRICE_INDEX, stockPrice.getId()).doc(new Gson().toJson(stockPrice), XContentType.JSON);
        } catch (Exception e) {
            System.out.println("error " + e);
            return null;
        }
    }


    public String getTodayDate() {
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(
                        QueryBuilders.boolQuery()
                )
                .from(0)
                .size(1)
                .sort("dayTime", SortOrder.DESC);
        return this.queryFrom(searchSourceBuilder).get(0).getDate();
    }

    public String getYesterdayDate(String today) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long todayTime;
        try {
            todayTime = sdf.parse(today).getTime();
        } catch (Exception e){
            todayTime = new Date().getTime();
        }
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(
                        QueryBuilders.boolQuery()
                        .filter(QueryBuilders.rangeQuery("dayTime").lt(todayTime))
                )
                .from(0)
                .size(1)
                .sort("dayTime", SortOrder.DESC);
        return this.queryFrom(searchSourceBuilder).get(0).getDate();
    }

}
