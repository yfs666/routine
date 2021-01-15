package com.yfs.es.train.estrain;

import com.yfs.es.train.estrain.service.StockProfitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StockProfitServiceTest {

    @Autowired
    private StockProfitService stockProfitService;

    @Test
    public void findUpListTest() {
        stockProfitService.findUpList();
        System.out.println(123);
    }

}
