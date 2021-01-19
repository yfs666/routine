package com.yfs.es.train.estrain;

import com.yfs.es.train.estrain.biz.BizService;
import com.yfs.es.train.estrain.service.StockProfitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StockProfitServiceTest {

    @Autowired
    private StockProfitService stockProfitService;

    @Autowired
    private BizService bizService;

    @Test
    public void findUpListTest() {
        stockProfitService.findUpList();
        System.out.println(123);
    }

    @Test
    public void queryMyStockTest() {
     bizService.queryMyStock();
    }

}
