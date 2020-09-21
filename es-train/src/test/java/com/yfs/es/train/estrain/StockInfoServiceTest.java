package com.yfs.es.train.estrain;


import com.google.common.base.Stopwatch;
import com.yfs.es.train.estrain.entity.StockInfo;
import com.yfs.es.train.estrain.entity.ThsPrice;
import com.yfs.es.train.estrain.service.StockInfoService;
import com.yfs.es.train.estrain.service.StockPriceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class StockInfoServiceTest {
    @Autowired
    private StockInfoService stockInfoService;

    @Autowired
    private StockPriceService stockPriceService;

    @Test
    public void pageTest() {
        List<StockInfo> stockInfos = stockInfoService.pageList(0, 100);
        System.out.println(stockInfos);
    }


    @Test
    public void initHistory() {
        List<StockInfo> stockInfos = stockInfoService.pageList(0, 10000);
        for (StockInfo stockInfo : stockInfos) {
            if (stockInfo.getCode().startsWith("60") || stockInfo.getCode().startsWith("000") || stockInfo.getCode().startsWith("001") ) {
                continue;
            }
            SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("code", stockInfo.getCode())).from(0).size(10);
            List<ThsPrice> existList = stockPriceService.queryFrom(searchSourceBuilder);
            if (CollectionUtils.isNotEmpty(existList)) {
                continue;
            }
            Stopwatch stopWatch = Stopwatch.createStarted();
//            List<List<ThsPrice>> listList = stockPriceService.initHistoryList(stockInfo.getSymbol());
//            for (List<ThsPrice> thsPrices : listList) {
//                stockPriceService.batchAdd(thsPrices);
//            }
            log.info("initHistory code is {}, cost {} ms", stockInfo.getCode(), stopWatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }



}
