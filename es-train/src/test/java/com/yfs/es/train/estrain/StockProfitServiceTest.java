package com.yfs.es.train.estrain;

import com.google.common.collect.Lists;
import com.yfs.es.train.estrain.biz.BizService;
import com.yfs.es.train.estrain.entity.StockProfit;
import com.yfs.es.train.estrain.entity.ThsPrice;
import com.yfs.es.train.estrain.service.StockPriceService;
import com.yfs.es.train.estrain.service.StockProfitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
public class StockProfitServiceTest {

    @Autowired
    private StockProfitService stockProfitService;

    @Autowired
    private BizService bizService;

    @Autowired
    private StockPriceService stockPriceService;

    @Test
    public void findUpListTest() {
        List<StockProfit> stockProfits = stockProfitService.findUpList();
        Map<String, StockProfit> stockProfitMap = stockProfits.stream().collect(Collectors.toMap(StockProfit::getSymbol, Function.identity(), (o1, o2) -> o1));
        List<String> symbols = stockProfits.stream().map(StockProfit::getSymbol).collect(Collectors.toList());
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(
                QueryBuilders.boolQuery()
                        .filter(QueryBuilders.termQuery("date","2021-02-22"))
                        .filter(QueryBuilders.termsQuery("symbol.keyword", symbols))
        ).from(0).size(10000);
        List<ThsPrice> thsPrices = stockPriceService.queryFrom(searchSourceBuilder).stream().filter(
                it-> {
                    StockProfit stockProfit = stockProfitMap.get(it.getSymbol());
                    if (stockProfit == null) {
                        return true;
                    }
                    return stockProfit.getProfit().multiply(BigDecimal.valueOf(40)).compareTo(it.getMarketValue()) > 0;
                }
        ).collect(Collectors.toList());
        System.out.println(thsPrices);
        Map<String, List<ThsPrice>> priceMap = thsPrices.stream().collect(Collectors.groupingBy(it -> {
            if (StockProfitService.YI.multiply(BigDecimal.valueOf(500)).compareTo(it.getMarketValue()) < 0) {
                return "大盘";
            }
            if (StockProfitService.YI.multiply(BigDecimal.valueOf(200)).compareTo(it.getMarketValue()) < 0) {
                return "中盘";
            }
            if (StockProfitService.YI.multiply(BigDecimal.valueOf(100)).compareTo(it.getMarketValue()) < 0) {
                return "中小盘";
            }
            if (StockProfitService.YI.multiply(BigDecimal.valueOf(50)).compareTo(it.getMarketValue()) < 0) {
                return "小盘";
            }
            return "超小盘";
        }));
        System.out.println(priceMap);
        for (Map.Entry<String, List<ThsPrice>> stringListEntry : priceMap.entrySet()) {
            System.out.print(stringListEntry.getKey() + "   ");
            System.out.println(stringListEntry.getValue().stream().map(ThsPrice::getCode).collect(Collectors.joining(" ")));
        }

    }

    @Test
    public void queryMyStockTest() {
        String date = null;
        if (StringUtils.isBlank(date)) {
            date = stockPriceService.getTodayDate();
        }
        List<String> symbols = bizService.queryMyStock();
        if (CollectionUtils.isEmpty(symbols)) {
            return ;
        }
        String yesterdayDate = stockPriceService.getYesterdayDate(date);
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(
                QueryBuilders.boolQuery()
                        .filter(QueryBuilders.termsQuery("date", Lists.newArrayList(date, yesterdayDate)))
                        .filter(QueryBuilders.termsQuery("symbol.keyword", symbols))
        ).from(0).size(10000);
        List<ThsPrice> thsPrices = stockPriceService.queryFrom(searchSourceBuilder);
        Map<String, List<ThsPrice>> datePriceMap = thsPrices.stream().collect(Collectors.groupingBy(ThsPrice::getDate));
        List<ThsPrice> todayPrices = datePriceMap.getOrDefault(date, Collections.emptyList());
        Map<String, ThsPrice> yesterdayPriceMap = datePriceMap.getOrDefault(date, Collections.emptyList()).stream().collect(Collectors.toMap(ThsPrice::getCode, Function.identity(), (o1, o2) -> o1));
        List<String> codes = todayPrices.stream().filter(todayPrice -> {
            if (todayPrice.getClose().compareTo(todayPrice.getMa10()) >= 0) {
                return true;
            }
            return Optional.ofNullable(yesterdayPriceMap.get(todayPrice.getCode())).map(it -> todayPrice.getMa10().compareTo(it.getMa10()) > 0).orElse(false);
        }).map(ThsPrice::getCode).collect(Collectors.toList());
        log.info(String.join(" ", codes));
    }

}
