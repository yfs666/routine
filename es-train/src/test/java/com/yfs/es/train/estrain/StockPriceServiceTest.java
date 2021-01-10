package com.yfs.es.train.estrain;

import com.yfs.es.train.estrain.entity.ThsPrice;
import com.yfs.es.train.estrain.service.StockPriceService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public void dateTest() {
        String todayDate = stockPriceService.getTodayDate();
        String yesterdayDate = stockPriceService.getYesterdayDate(todayDate);
        System.out.println(yesterdayDate);
    }

}
