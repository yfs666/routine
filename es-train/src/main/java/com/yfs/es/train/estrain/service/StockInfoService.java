package com.yfs.es.train.estrain.service;

import com.google.gson.Gson;
import com.yfs.es.train.estrain.entity.StockInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class StockInfoService {

    private static final String STOCK_INFO_INDEX = "ths_stock_info";


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 批量添加
     *
     * @param stockInfos 批量数据
     * @throws IOException
     */
    public void batchAdd(List<StockInfo> stockInfos) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(STOCK_INFO_INDEX);
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        if (!exists) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(STOCK_INFO_INDEX);
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        }
        Gson gson = new Gson();
        BulkRequest bulkRequest = Requests.bulkRequest().timeout("60s");
        stockInfos.stream().map(gson::toJson).forEach(it -> {
            bulkRequest.add(Requests.indexRequest(STOCK_INFO_INDEX).source(it, XContentType.JSON));
        });
        BulkResponse bulkItemResponses = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        boolean hasFailures = bulkItemResponses.hasFailures();
        log.info(bulkItemResponses.toString());
    }


    /**
     * 修改
     *
     * @param stockInfo 基本信息
     * @throws IOException
     */
    public void update(StockInfo stockInfo) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(STOCK_INFO_INDEX, stockInfo.getId());
        updateRequest.doc(new Gson().toJson(stockInfo), XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        RestStatus status = updateResponse.status();
        System.out.println(status);
    }


    public List<StockInfo> pageList(int start, int pageSize) {
        return this.queryBy(SearchSourceBuilder.searchSource().from(start).size(pageSize));
    }

    public List<StockInfo> queryBy(SearchSourceBuilder searchSourceBuilder) {
        try {
            Gson gson = new Gson();
            SearchResponse searchResponse = restHighLevelClient.search(Requests.searchRequest(STOCK_INFO_INDEX).source(searchSourceBuilder), RequestOptions.DEFAULT);
            return Arrays.stream(searchResponse.getHits().getHits()).map(it -> {
                StockInfo stockInfo = gson.fromJson(it.getSourceAsString(), StockInfo.class);
                stockInfo.setId(it.getId());
                return stockInfo;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /**
     * 比较是否存在，存在则不管，不存在就直接保存
     * @param stockInfos 今日所有的数据
     */
    public void compareAndSave(List<StockInfo> stockInfos) {
        List<String> symbols = this.pageList(0, 5000).stream().map(StockInfo::getSymbol).collect(Collectors.toList());
        List<StockInfo> newStockInfoList = stockInfos.stream().filter(it -> !symbols.contains(it.getSymbol())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(newStockInfoList)) {
            return;
        }
        try {
            log.info("compareAndSave find new stock info list , list is {}", new Gson().toJson(newStockInfoList));
            this.batchAdd(newStockInfoList);
        } catch (Exception e) {
            log.error("compareAndSave error ", e);
        }
    }

}
