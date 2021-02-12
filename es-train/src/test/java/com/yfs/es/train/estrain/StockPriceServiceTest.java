package com.yfs.es.train.estrain;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yfs.es.train.estrain.entity.StockPrice;
import com.yfs.es.train.estrain.entity.ThsPrice;
import com.yfs.es.train.estrain.entity.UpDown;
import com.yfs.es.train.estrain.service.StockPriceService;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootTest
public class StockPriceServiceTest {

    @Autowired
    private StockPriceService stockPriceService;

    @Test
    public void queryTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2020-01-01");
        Date end = sdf.parse("2021-01-01");
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.boolQuery()
                        .filter(QueryBuilders.termQuery("code", "600093"))
                        .filter(QueryBuilders.rangeQuery("dayTime").gte(start.getTime()).lte(end.getTime()))
                )
                .from(0).size(10000);
//        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("code", "600004")).from(0).size(10000);
        List<ThsPrice> thsPrices = stockPriceService.queryFrom(searchSourceBuilder);
        System.out.println(thsPrices);
    }

    @Test
    public void Test() {
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.boolQuery()
                        .filter(QueryBuilders.termQuery("date","2021-02-05"))
                )
                .from(0).size(10000);
        List<ThsPrice> thsPrices = stockPriceService.queryFrom(searchSourceBuilder);
//        thsPrices.forEach(stockPriceService::delete);
        System.out.println(thsPrices);
    }

    @Test
    public void dateTest() {
        String todayDate = stockPriceService.getTodayDate();
        String yesterdayDate = stockPriceService.getYesterdayDate(todayDate);
        System.out.println(yesterdayDate);
    }


    @Test
    public void testHeavyTest() throws ParseException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i <= 41; i++) {
            Integer start = 100 * i;
            List<ThsPrice> thsPrices = this.getByStart(start);
            System.out.println("start is " + i + ", result is " + thsPrices.get(thsPrices.size() - 1).getClose());
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Test
    public void getAVGTest() {
        List<UpDown> upDowns = stockPriceService.avgUpDown(stockPriceService.getTodayDate());
        upDowns.forEach(it -> System.out.println(it.getCode() + "," + it.getPercent()));
    }


    private List<ThsPrice> getByStart(Integer start) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long startTime = sdf.parse("2021-01-01").getTime();
        if (start == null) start = 0;
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(QueryBuilders.boolQuery()
                        .filter(QueryBuilders.termQuery("date","2021-02-05"))
                )
                .from(0).size(10000);
        List<ThsPrice> thsPrices = stockPriceService.queryFrom(searchSourceBuilder);
        thsPrices.sort(Comparator.comparing(ThsPrice::getMarketValue).reversed());
        int end = start + 100;
        List<String> symbols = thsPrices.subList(start, end).stream().map(ThsPrice::getSymbol).collect(Collectors.toList());
        List<ThsPrice> selectPrices = Flux.fromIterable(symbols)
                .subscribeOn(Schedulers.parallel())
                .buffer(20)
                .map(it -> {
                    SearchSourceBuilder searchSourceBuilderDetail = SearchSourceBuilder.searchSource()
                            .query(QueryBuilders.boolQuery()
                                    .filter(QueryBuilders.termsQuery("symbol.keyword", it))
                                    .filter(QueryBuilders.rangeQuery("dayTime").gte(startTime))
                            )
                            .from(0).size(10000).sort("dayTime", SortOrder.DESC);
                    return stockPriceService.queryFrom(searchSourceBuilderDetail);
                }).flatMapIterable(Function.identity())
                .collectList().block();
        Map<Long, List<ThsPrice>> priceGroup = selectPrices.stream().filter(it -> it.getDayTime() > startTime).filter(it -> it.getClosePercent().intValue() <= 21).collect(Collectors.groupingBy(ThsPrice::getDayTime));
        List<ThsPrice> sumPriceList = priceGroup.entrySet().stream().map(priceGroupEntry -> {
            List<ThsPrice> thsPriceList = priceGroupEntry.getValue();
            if (CollectionUtils.isEmpty(thsPriceList)) {
                return null;
            }
            double avgOpen = thsPriceList.stream().mapToDouble(it -> it.getOpenPercent().doubleValue()).average().orElse(0);
            double avgClose = thsPriceList.stream().mapToDouble(it -> it.getClosePercent().doubleValue()).average().orElse(0);
            double avgHigh = thsPriceList.stream().mapToDouble(it -> it.getHighPercent().doubleValue()).average().orElse(0);
            double avgLow = thsPriceList.stream().mapToDouble(it -> it.getLowPercent().doubleValue()).average().orElse(0);
            ThsPrice sumPrice = new ThsPrice();
            sumPrice.setDate(thsPriceList.get(0).getDate());
            sumPrice.setDayTime(thsPriceList.get(0).getDayTime());
            sumPrice.setOpenPercent(BigDecimal.valueOf(avgOpen));
            sumPrice.setClosePercent(BigDecimal.valueOf(avgClose));
            sumPrice.setHighPercent(BigDecimal.valueOf(avgHigh));
            sumPrice.setLowPercent(BigDecimal.valueOf(avgLow));
            return sumPrice;
        }).filter(Objects::nonNull).sorted(Comparator.comparing(ThsPrice::getDayTime)).collect(Collectors.toList());
        BigDecimal currentSum = BigDecimal.valueOf(10000);
        for (ThsPrice thsPrice : sumPriceList) {
            thsPrice.setOpen(currentSum.multiply(thsPrice.getOpenPercent().divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP).add(BigDecimal.ONE)).setScale(2, BigDecimal.ROUND_HALF_UP));
            thsPrice.setClose(currentSum.multiply(thsPrice.getClosePercent().divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP).add(BigDecimal.ONE)).setScale(2, BigDecimal.ROUND_HALF_UP));
            thsPrice.setHigh(currentSum.multiply(thsPrice.getHighPercent().divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP).add(BigDecimal.ONE)).setScale(2, BigDecimal.ROUND_HALF_UP));
            thsPrice.setLow(currentSum.multiply(thsPrice.getLowPercent().divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP).add(BigDecimal.ONE)).setScale(2, BigDecimal.ROUND_HALF_UP));
            currentSum = thsPrice.getClose();
        }
        return sumPriceList;
    }


}
