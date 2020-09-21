package com.yfs.es.train.estrain;


import com.yfs.es.train.estrain.entity.StockInfo;
import com.yfs.es.train.estrain.service.StockInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StockInfoServiceTest {
    @Autowired
    private StockInfoService stockInfoService;

    @Test
    public void pageTest() {
        List<StockInfo> stockInfos = stockInfoService.pageList(0, 100);
        System.out.println(stockInfos);
    }


}
