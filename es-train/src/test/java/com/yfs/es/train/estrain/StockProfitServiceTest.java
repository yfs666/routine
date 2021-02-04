package com.yfs.es.train.estrain;

import com.google.common.collect.Lists;
import com.yfs.es.train.estrain.biz.BizService;
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
        stockProfitService.findUpList();
        System.out.println(123);
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
