package com.yfs.es.train.estrain.controller;

import com.alibaba.excel.EasyExcel;
import com.google.gson.Gson;
import com.yfs.es.train.estrain.biz.BizService;
import com.yfs.es.train.estrain.entity.StockInfo;
import com.yfs.es.train.estrain.entity.ThsPrice;
import com.yfs.es.train.estrain.excel.ExcelListener;
import com.yfs.es.train.estrain.service.StockInfoService;
import com.yfs.es.train.estrain.service.StockPriceService;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/import")
public class ImportController {

    @Autowired
    private StockInfoService stockInfoService;

    @Autowired
    private StockPriceService stockPriceService;

    @Autowired
    private BizService bizService;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @RequestMapping(value = "/importDayLine", method = RequestMethod.POST)
    public Map<String, Object> importDayLine(MultipartFile file) throws IOException {
        ExcelListener excelListener = new ExcelListener(file.getOriginalFilename().split("\\.")[0]);
        EasyExcel.read(file.getInputStream(), excelListener).sheet().headRowNumber(1).doRead();
        stockInfoService.compareAndSave(excelListener.getStockInfoList());
        stockPriceService.compareAndAdd(excelListener.getThsPriceList());
        bizService.handleDayData();
        return Collections.emptyMap();
    }

    private final ExecutorService executorService = Executors.newFixedThreadPool(50);

    @RequestMapping(value = "/handle", method = RequestMethod.GET)
    public Map<String, Object> handle() throws IOException {
        List<String> codes = stockInfoService.pageList(0, 5000).stream().map(StockInfo::getCode).collect(Collectors.toList());
        Flux.fromIterable(codes)
                .subscribeOn(Schedulers.fromExecutorService(executorService))
                .doOnNext(this::handlePercent)
                .subscribe();

        return Collections.emptyMap();
    }

    public void handlePercent(String code) {
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("code", code)).from(0).size(10000);
        List<ThsPrice> thsPrices = stockPriceService.queryFrom(searchSourceBuilder);
        if (CollectionUtils.isEmpty(thsPrices)) {
            return;
        }
        thsPrices.sort(Comparator.comparing(ThsPrice::getDayTime));
        ThsPrice yesterdayPrice = null;
        BulkRequest bulkRequest = Requests.bulkRequest();
        boolean added = false;
        for (ThsPrice thsPrice : thsPrices) {
            if (yesterdayPrice == null) {
                yesterdayPrice = thsPrice;
            }
            thsPrice.setYesterdayClose(yesterdayPrice.getClose());
            thsPrice.setOpenPercent(thsPrice.getOpen().divide(yesterdayPrice.getClose(),4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)));
            thsPrice.setClosePercent(thsPrice.getClose().divide(yesterdayPrice.getClose(),4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)));
            thsPrice.setLowPercent(thsPrice.getLow().divide(yesterdayPrice.getClose(),4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)));
            thsPrice.setHighPercent(thsPrice.getHigh().divide(yesterdayPrice.getClose(),4, BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)));
            bulkRequest.add(new UpdateRequest("ths_stock_price", thsPrice.getId()).doc(new Gson().toJson(thsPrice), XContentType.JSON));
            added = true;
            yesterdayPrice = thsPrice;
        }
        try {
            if (added) {
                BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(code);


    }

}
