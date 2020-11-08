package com.yfs.es.train.estrain.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.yfs.es.train.estrain.LimitEnum;
import com.yfs.es.train.estrain.entity.CountShowDetailVO;
import com.yfs.es.train.estrain.entity.CountShowVO;
import com.yfs.es.train.estrain.entity.KLineVO;
import com.yfs.es.train.estrain.entity.MarketKLineShowVO;
import com.yfs.es.train.estrain.entity.StockInfo;
import com.yfs.es.train.estrain.entity.StockPrice;
import com.yfs.es.train.estrain.entity.ThsPrice;
import com.yfs.es.train.estrain.entity.UpDown;
import com.yfs.es.train.estrain.service.StockInfoService;
import com.yfs.es.train.estrain.service.StockPriceService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.util.function.Tuple2;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class IndexController {


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private StockPriceService stockPriceService;

    @Autowired
    private StockInfoService stockInfoService;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = {"/index", ""}, method = RequestMethod.GET)
    public String index() {
        Map<String, Object> result = Maps.newHashMap();
        result.put("code", 200);
        return "<h1>一个大写的 帅 </h1>";
    }


    @RequestMapping(value = "listDayLine", method = RequestMethod.GET)
    public Map<String, Object> listDayLine(@RequestParam String code, @RequestParam(name = "days", required = false) Integer days) {
        List<ThsPrice> thsPrices = stockPriceService.queryFrom(SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("code", code)).sort("dayTime", SortOrder.DESC).from(0).size(days == null ? 100 : days));
        if (CollectionUtils.isEmpty(thsPrices)) {
            return Collections.emptyMap();
        }
        Collections.reverse(thsPrices);
        ThsPrice prePrice = thsPrices.get(0);
        List<ThsPrice> todayOpenPriceList = Lists.newArrayList();
        for (ThsPrice thsPrice : thsPrices) {
            thsPrice.setYesterdayClose(prePrice.getClose());
            thsPrice.setOpenPercent(thsPrice.getOpen().divide(thsPrice.getYesterdayClose(), 4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100.0)));
            thsPrice.setClosePercent(thsPrice.getClose().divide(thsPrice.getYesterdayClose(), 4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100.0)));
            thsPrice.setHighPercent(thsPrice.getHigh().divide(thsPrice.getYesterdayClose(), 4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100.0)));
            thsPrice.setLowPercent(thsPrice.getLow().divide(thsPrice.getYesterdayClose(), 4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100.0)));
            prePrice = thsPrice;
            ThsPrice openData = new ThsPrice();
            openData.setOpenPercent(BigDecimal.ZERO);
            openData.setClosePercent(thsPrice.getClose().divide(thsPrice.getOpen(), 4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100.0)));
            openData.setHighPercent(thsPrice.getHigh().divide(thsPrice.getOpen(), 4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100.0)));
            openData.setLowPercent(thsPrice.getLow().divide(thsPrice.getOpen(), 4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100.0)));
            todayOpenPriceList.add(openData);
        }
        Map<String, Object> result = Maps.newHashMap();
        result.put("code", 0);
        result.put("list", thsPrices);
        result.put("openList", todayOpenPriceList);
        return result;
    }



    @RequestMapping(value = "/avg", method = RequestMethod.GET)
    public Map<String, Object> avg(@RequestParam(name = "t", required = false) String t) throws IOException {
        HashMap<String, Object> result = Maps.newHashMap();
        if (StringUtils.isBlank(t)) {
            t = stockPriceService.getTodayDate();
        }
        String y = stockPriceService.getYesterdayDate(t);
        SearchRequest searchRequest = Requests.searchRequest(StockPriceService.STOCK_PRICE_INDEX);
        searchRequest.source().query(QueryBuilders.termsQuery("date", Lists.newArrayList(y, t))).from(0).size(10000);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        Map<String, List<ThsPrice>> group = Arrays.stream(hits).map(hit -> new Gson().fromJson(hit.getSourceAsString(), ThsPrice.class))
                .collect(Collectors.groupingBy(ThsPrice::getDate));
        Map<String, ThsPrice> yesterdayMap = group.get(y).stream().collect(Collectors.toMap(ThsPrice::getCode, Function.identity(), (o1, o2) -> o1));
        if (group.size() <= 1) {
            result.put("code", 201);
            return result;
        }
        int count = 0;
        BigDecimal closeSum = BigDecimal.ZERO;
        BigDecimal highSum = BigDecimal.ZERO;
        BigDecimal lowSum = BigDecimal.ZERO;
        List<UpDown> closeUpDowns = Lists.newArrayList();
        List<UpDown> highUpDowns = Lists.newArrayList();
        List<UpDown> lowUpDowns = Lists.newArrayList();
        List<KLineVO> kLineVOSum = Lists.newArrayList();
        List<KLineVO> kLineVO60x = Lists.newArrayList();
        List<KLineVO> kLineVO000 = Lists.newArrayList();
        List<KLineVO> kLineVO002 = Lists.newArrayList();
        List<KLineVO> kLineVO300 = Lists.newArrayList();
        List<KLineVO> kLineVO688 = Lists.newArrayList();
        int myCount = 0;
        for (ThsPrice stockPrice : group.get(t)) {
            ThsPrice yesterdayPrice = yesterdayMap.get(stockPrice.getCode());
            if (yesterdayPrice != null) {
                if (stockPrice.getHigh().divide(yesterdayPrice.getClose(), 6, BigDecimal.ROUND_HALF_UP).doubleValue() > 1.005 &&
                        stockPrice.getLow().divide(yesterdayPrice.getClose(), 6, BigDecimal.ROUND_HALF_UP).doubleValue() < 0.995) {
                    myCount++;
                    System.out.println(myCount + "符合条件：" + stockPrice.getCode());
                }
                count++;
                BigDecimal closePercent = stockPrice.getClose().divide(yesterdayPrice.getClose(), 10, BigDecimal.ROUND_HALF_UP);
                BigDecimal highPercent = stockPrice.getHigh().divide(yesterdayPrice.getClose(), 10, BigDecimal.ROUND_HALF_UP);
                BigDecimal lowPercent = stockPrice.getLow().divide(yesterdayPrice.getClose(), 10, BigDecimal.ROUND_HALF_UP);
                closeSum = closeSum.add(closePercent);
                highSum = highSum.add(highPercent);
                lowSum = lowSum.add(lowPercent);
                closeUpDowns.add(new UpDown(stockPrice.getCode(), closePercent.subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100))));
                highUpDowns.add(new UpDown(stockPrice.getCode(), highPercent.subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100))));
                lowUpDowns.add(new UpDown(stockPrice.getCode(), lowPercent.subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100))));
                KLineVO kLineVO = new KLineVO(stockPrice.getCode(), stockPrice.getOpen(), stockPrice.getOpen(), stockPrice.getClose(), stockPrice.getHigh(), stockPrice.getLow());
                kLineVOSum.add(kLineVO);
                if (stockPrice.getCode().startsWith("60")) {
                    kLineVO60x.add(kLineVO);
                }
                if (stockPrice.getCode().startsWith("000")) {
                    kLineVO000.add(kLineVO);
                }
                if (stockPrice.getCode().startsWith("002")) {
                    kLineVO002.add(kLineVO);
                }
                if (stockPrice.getCode().startsWith("300")) {
                    kLineVO300.add(kLineVO);
                }
                if (stockPrice.getCode().startsWith("688")) {
                    kLineVO688.add(kLineVO);
                }
            }
        }
        CountShowVO countShowVO = new CountShowVO();
        BigDecimal closeUpPercent = closeSum.divide(new BigDecimal(count), 6, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100));
        BigDecimal highUpPercent = highSum.divide(new BigDecimal(count), 6, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100));
        BigDecimal lowUpPercent = lowSum.divide(new BigDecimal(count), 6, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100));
        countShowVO.setCount(count);
        countShowVO.setCloseAvg(closeUpPercent);
        countShowVO.setHighAvg(highUpPercent);
        countShowVO.setLowAvg(lowUpPercent);
        Map<LimitEnum, Long> collectCloseSum = closeUpDowns.stream().collect(Collectors.groupingBy(UpDown::getLimitEnum, Collectors.counting()));
        countShowVO.setCloseAvgSum(getDetailVos(collectCloseSum));
        Map<LimitEnum, Long> collectHighSum = highUpDowns.stream().collect(Collectors.groupingBy(UpDown::getLimitEnum, Collectors.counting()));
        countShowVO.setHighAvgSum(getDetailVos(collectHighSum));
        Map<LimitEnum, Long> collectLowSum = lowUpDowns.stream().collect(Collectors.groupingBy(UpDown::getLimitEnum, Collectors.counting()));
        countShowVO.setLowAvgSum(getDetailVos(collectLowSum));
        Map<String, Map<LimitEnum, Long>> collect = closeUpDowns.stream().collect(Collectors.groupingBy(it -> {
            if (it.getCode().startsWith("60")) {
                return "60";
            } else {
                return it.getCode().substring(0, 3);
            }
        }, Collectors.groupingBy(UpDown::getLimitEnum, Collectors.counting())));
        for (Map.Entry<String, Map<LimitEnum, Long>> stringMapEntry : collect.entrySet()) {
            // code标识对应的数据
            List<CountShowDetailVO> detailVos = getDetailVos(stringMapEntry.getValue());
            if ("60".equals(stringMapEntry.getKey())) {
                countShowVO.setCloseAvg60x(detailVos);
            }
            if ("000".equals(stringMapEntry.getKey())) {
                countShowVO.setCloseAvg000(detailVos);
            }
            if ("002".equals(stringMapEntry.getKey())) {
                countShowVO.setCloseAvg002(detailVos);
            }
            if ("300".equals(stringMapEntry.getKey())) {
                countShowVO.setCloseAvg300(detailVos);
            }
            if ("688".equals(stringMapEntry.getKey())) {
                countShowVO.setCloseAvg688(detailVos);
            }
        }
        Map<String, Map<LimitEnum, Long>> collectHigh = highUpDowns.stream().collect(Collectors.groupingBy(it -> {
            if (it.getCode().startsWith("60")) {
                return "60";
            } else {
                return it.getCode().substring(0, 3);
            }
        }, Collectors.groupingBy(UpDown::getLimitEnum, Collectors.counting())));
        for (Map.Entry<String, Map<LimitEnum, Long>> stringMapEntry : collectHigh.entrySet()) {
            // code标识对应的数据
            List<CountShowDetailVO> detailVos = getDetailVos(stringMapEntry.getValue());
            if ("60".equals(stringMapEntry.getKey())) {
                countShowVO.setHighAvg60x(detailVos);
            }
            if ("000".equals(stringMapEntry.getKey())) {
                countShowVO.setHighAvg000(detailVos);
            }
            if ("002".equals(stringMapEntry.getKey())) {
                countShowVO.setHighAvg002(detailVos);
            }
            if ("300".equals(stringMapEntry.getKey())) {
                countShowVO.setHighAvg300(detailVos);
            }
            if ("688".equals(stringMapEntry.getKey())) {
                countShowVO.setHighAvg688(detailVos);
            }
        }
        Map<String, Map<LimitEnum, Long>> collectLow = lowUpDowns.stream().collect(Collectors.groupingBy(it -> {
            if (it.getCode().startsWith("60")) {
                return "60";
            } else {
                return it.getCode().substring(0, 3);
            }
        }, Collectors.groupingBy(UpDown::getLimitEnum, Collectors.counting())));
        for (Map.Entry<String, Map<LimitEnum, Long>> stringMapEntry : collectLow.entrySet()) {
            // code标识对应的数据
            List<CountShowDetailVO> detailVos = getDetailVos(stringMapEntry.getValue());
            if ("60".equals(stringMapEntry.getKey())) {
                countShowVO.setLowAvg60x(detailVos);
            }
            if ("000".equals(stringMapEntry.getKey())) {
                countShowVO.setLowAvg000(detailVos);
            }
            if ("002".equals(stringMapEntry.getKey())) {
                countShowVO.setLowAvg002(detailVos);
            }
            if ("300".equals(stringMapEntry.getKey())) {
                countShowVO.setLowAvg300(detailVos);
            }
            if ("300".equals(stringMapEntry.getKey())) {
                countShowVO.setLowAvg688(detailVos);
            }
        }

        result.put("code", 200);
        result.put("countShowVO", countShowVO);
        result.put("marketLine", new MarketKLineShowVO(kLineVOSum, kLineVO60x, kLineVO000, kLineVO002, kLineVO300, kLineVO688));
        return result;
    }

    private List<CountShowDetailVO> getDetailVos(Map<LimitEnum, Long> limitCountMap) {
        List<Tuple2<LimitEnum, LimitEnum>> enumGroup = LimitEnum.getEnumGroup();
        return enumGroup.stream().map(it -> {
            CountShowDetailVO detailVO = new CountShowDetailVO();
            detailVO.setLimitName(it.getT1().limitName);
            detailVO.setLimitUpCount(limitCountMap.getOrDefault(it.getT1(), 0L).intValue());
            detailVO.setLimitDownCount(limitCountMap.getOrDefault(it.getT2(), 0L).intValue());
            return detailVO;
        }).sorted(Comparator.<CountShowDetailVO>comparingDouble(it -> Double.parseDouble(it.getLimitName().split("~")[0])).thenComparingInt(it -> it.getLimitName().length())).collect(Collectors.toList());
    }


    @RequestMapping(value = "/getMyToday", method = RequestMethod.GET)
    public List<String> getMyToday(@RequestParam(name = "date", required = false) String date) throws Exception {
        if (StringUtils.isBlank(date)) {
            date = stockPriceService.getTodayDate();
        }
        SearchRequest searchRequest = Requests.searchRequest(StockPriceService.STOCK_PRICE_INDEX);
        searchRequest.source()
                .query(
                        QueryBuilders.boolQuery()
                                .filter(QueryBuilders.termQuery("date", StringUtils.isNotBlank(date) ? date : sdf.format(new Date())))
                                .filter(QueryBuilders.rangeQuery("highBefore").gte(50))
                ).from(0).size(2000);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("共 " + searchResponse.getHits().getTotalHits().value + "个");
        int count = 0;
        List<String> stocks = Lists.newArrayList();
        if (searchResponse.getHits().getTotalHits().value > 0) {
            stocks = Arrays.stream(searchResponse.getHits().getHits())
                    .map(hit -> {
                        ThsPrice stockPrice = new Gson().fromJson(hit.getSourceAsString(), ThsPrice.class);
                        stockPrice.setId(hit.getId());
                        return stockPrice;
                    })
                    .filter(ThsPrice::allUp)
//                    .filter(it->it.getClose().doubleValue()>it.getMa5().doubleValue())
                    .sorted(Comparator.comparing(it -> it.getMa5().divide(it.getMa30(), 5, BigDecimal.ROUND_HALF_UP)))
                    .map(ThsPrice::getCode)
                    .collect(Collectors.toList());
            for (String code : stocks) {
                count++;
//                System.out.println(stockPrice.getCode() + "    " + stockPrice.getHighBefore());
                System.out.print(code + " ");
                if (count % 10 == 0) {
                    System.out.println();
                }
            }
        }
        return stocks;
    }

    @RequestMapping(value = "/findTopUp", method = RequestMethod.GET)
    public List<UpDown> findTopUp(Integer days) throws IOException {
        if (days == null) days = 10;
        long endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        List<StockInfo> stockInfos = stockInfoService.pageList(0, 4000);
        List<UpDown> upDowns = Lists.newArrayList();
        for (StockInfo stockInfo : stockInfos) {
            List<ThsPrice> stockPrices = stockPriceService.queryBy(stockInfo.getCode(), 0, days, 0L, endTime, SortOrder.DESC);
            if (CollectionUtils.isEmpty(stockPrices) || stockPrices.size() < days) {
                continue;
            }
            BigDecimal percent = stockPrices.get(0).getClose().divide(stockPrices.get(stockPrices.size() - 1).getOpen(), 4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE);
            upDowns.add(new UpDown(stockInfo.getCode(), percent));
        }
        upDowns.sort(Comparator.comparing(UpDown::getPercent).reversed());
        for (int i = 0; i < 100; i++) {
            System.out.println(upDowns.get(i).getCode() + " ~ " + i + " ~ " + upDowns.get(i).getPercent());
        }
        upDowns.sort(Comparator.comparing(UpDown::getPercent).reversed());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < 500; i++) {
            System.out.print(upDowns.get(i).getCode() + " ");
        }
        return upDowns;
    }


}
