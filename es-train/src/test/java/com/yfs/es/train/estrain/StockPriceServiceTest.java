package com.yfs.es.train.estrain;

import com.yfs.es.train.estrain.entity.ThsPrice;
import com.yfs.es.train.estrain.service.StockPriceService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StockPriceServiceTest {

    @Autowired
    private StockPriceService stockPriceService;

    @Test
    public void queryTest() {
        SearchRequest searchRequest = Requests.searchRequest(StockPriceService.STOCK_PRICE_INDEX);
        searchRequest.source().query(QueryBuilders.termQuery("date", "2020-09-18")).from(0).size(10000);
        List<ThsPrice> thsPrices = stockPriceService.queryFrom(searchRequest);
        System.out.println(thsPrices);
    }

}
