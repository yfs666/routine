package com.yfs.es.train.estrain.biz;

import com.google.gson.Gson;
import com.yfs.es.train.estrain.entity.StockInfo;
import com.yfs.es.train.estrain.entity.StockProfit;
import com.yfs.es.train.estrain.entity.ThsPrice;
import com.yfs.es.train.estrain.service.StockInfoService;
import com.yfs.es.train.estrain.service.StockPriceService;
import com.yfs.es.train.estrain.service.StockProfitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Slf4j
@Service
public class BizService {
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private StockPriceService stockPriceService;
    @Autowired
    private StockProfitService stockProfitService;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    private static final BigDecimal compare = BigDecimal.valueOf(-0.01);
    private static final BigDecimal compare2020 = BigDecimal.valueOf(0.1);

    public List<String> queryMyStock() {
        SearchSourceBuilder searchSourceBuilder2018 = SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("reportName.keyword", "2018年报")).from(0).size(10000);
        SearchSourceBuilder searchSourceBuilder2019 = SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("reportName.keyword", "2019年报")).from(0).size(10000);
        SearchSourceBuilder searchSourceBuilder2020 = SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("reportName.keyword", "2020三季报")).from(0).size(10000);
        List<String> symbols2018 = stockProfitService.queryFrom(searchSourceBuilder2018).stream()
                .filter(it -> it.getProfit().compareTo(BigDecimal.ZERO) > 0)
                .filter(it -> it.getProfitPercent().compareTo(compare) > 0)
                .map(StockProfit::getSymbol).collect(Collectors.toList());
        List<String> symbols2019 = stockProfitService.queryFrom(searchSourceBuilder2019).stream()
                .filter(it -> it.getProfit().compareTo(BigDecimal.ZERO) > 0)
                .filter(it -> it.getProfitPercent().compareTo(compare) > 0)
                .map(StockProfit::getSymbol).collect(Collectors.toList());
        List<String> symbols2020 = stockProfitService.queryFrom(searchSourceBuilder2020).stream()
                .filter(it -> it.getProfit().compareTo(BigDecimal.ZERO) > 0)
                .filter(it -> it.getProfitPercent().compareTo(compare2020) > 0)
                .map(StockProfit::getSymbol).collect(Collectors.toList());
        return symbols2020.stream().filter(symbols2018::contains).filter(symbols2019::contains).collect(Collectors.toList());
//        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
//                .query(
//                        QueryBuilders.boolQuery()
//                                .filter(QueryBuilders.termQuery("date", date))
//                ).from(0).size(10000);
//        List<ThsPrice> thsPrices = stockPriceService.queryFrom(searchSourceBuilder);
//        List<String> finalSymbols202 = symbols2020;
//        return thsPrices.stream().filter(ThsPrice::allUp).filter(it-> finalSymbols202.contains(it.getSymbol())).map(ThsPrice::getSymbol).collect(Collectors.toList());

    }

    public void handleDayData() {
        int start = 0;
        int pageSize = 50;
        while (true) {
            List<StockInfo> stockInfos = stockInfoService.pageList(start, pageSize);
            if (CollectionUtils.isEmpty(stockInfos)) {
                break;
            }
            start = start + pageSize;
            this.handleData(stockInfos);
            log.info("end>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> start is " + start);
        }
    }

    public void handleData(List<StockInfo> stockInfos) {
//        this.handleHighBefore(stockInfos);
        this.handleMa(stockInfos);
    }


    public void handleHighBefore(List<StockInfo> stockInfos) {
        if (CollectionUtils.isEmpty(stockInfos)) {
            return;
        }
        long startTime = DateUtils.addDays(new Date(), -1000).getTime();
        long endTime = System.currentTimeMillis();
        List<String> codes = Flux.fromIterable(stockInfos)
                .flatMap(it -> Mono.fromSupplier(() -> stockPriceService.highBefore(it, startTime, endTime)).subscribeOn(Schedulers.elastic()))
                .collectList()
                .block();
        log.info(new Gson().toJson(codes));
    }

    public void handleMa(List<StockInfo> stockInfos) {
        if (CollectionUtils.isEmpty(stockInfos)) {
            return;
        }
        long startTime = DateUtils.addDays(new Date(), -100).getTime();
        long endTime = System.currentTimeMillis();
        Flux.fromIterable(stockInfos)
                .flatMap(it -> Mono.fromSupplier(() -> this.correct(it.getCode(), startTime, endTime)).subscribeOn(Schedulers.elastic()))
                .collectList()
                .block();
    }


    private String correct(String code, long startTime, long endTime) {
        try {
            stockPriceService.correctData(code, startTime, endTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }
}
