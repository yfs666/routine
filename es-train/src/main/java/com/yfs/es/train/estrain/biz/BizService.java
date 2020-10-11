package com.yfs.es.train.estrain.biz;

import com.yfs.es.train.estrain.entity.StockInfo;
import com.yfs.es.train.estrain.service.StockInfoService;
import com.yfs.es.train.estrain.service.StockPriceService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class BizService {
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private StockPriceService stockPriceService;

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
            System.out.println("end>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> start is " + start);
        }
    }

    public void handleData(List<StockInfo> stockInfos) {
        this.handleHighBefore(stockInfos);
        this.handleMa(stockInfos);
    }


    public void handleHighBefore(List<StockInfo> stockInfos) {
        if (CollectionUtils.isEmpty(stockInfos)) {
            return;
        }
        long startTime = DateUtils.addDays(new Date(), -200).getTime();
        long endTime = System.currentTimeMillis();
        List<String> codes = Flux.fromIterable(stockInfos)
                .flatMap(it -> Mono.fromSupplier(() -> stockPriceService.highBefore(it, startTime, endTime)).subscribeOn(Schedulers.elastic()))
                .collectList()
                .block();
        System.out.println(codes);
    }

    public void handleMa(List<StockInfo> stockInfos) {
        if (CollectionUtils.isEmpty(stockInfos)) {
            return;
        }
        long startTime = DateUtils.addDays(new Date(), -200).getTime();
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
