package com.yfs.es.train.estrain.service;

import com.google.gson.Gson;
import com.yfs.es.train.estrain.entity.EatLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class EatLogService {

    private static final String EAT_LOG_INDEX = "eat_log";


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public void addIndex() throws IOException {
        CreateIndexRequest index = new CreateIndexRequest(EAT_LOG_INDEX);
        CreateIndexResponse createPriceIndexResponse = restHighLevelClient.indices().create(index, RequestOptions.DEFAULT);
        System.out.println(createPriceIndexResponse);
    }

    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(EAT_LOG_INDEX);
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request,
                RequestOptions.DEFAULT); System.out.println(delete.isAcknowledged());
    }

    public void addEatLog(BigDecimal count, LocalTime drugTime, LocalTime breakFastTime, String operator) throws IOException {
        EatLog eatLog = new EatLog();
        LocalDate today = LocalDate.now();
        eatLog.setCount(count);
        eatLog.setDate(today.format(dateFormatter));
        eatLog.setDrugTime(drugTime);
        eatLog.setBreakFastTime(breakFastTime);
        eatLog.setOperator(operator);
        eatLog.setOperateTime(LocalDateTime.now());
        this.addEatLog(eatLog);
    }


    public void addEatLog(EatLog eatLog) throws IOException {
        BulkRequest bulkRequest = Requests.bulkRequest().timeout("60s");
        bulkRequest.add(Requests.indexRequest(EAT_LOG_INDEX).source(new Gson().toJson(eatLog), XContentType.JSON));
        BulkResponse bulkItemResponses = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        boolean hasFailures = bulkItemResponses.hasFailures();
        if (hasFailures) {
            throw new RuntimeException("发现异常，请检查");
        }
    }

    public EatLog queryByDate(LocalDate localDate) {
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(QueryBuilders.termQuery("date", localDate.format(dateFormatter)));
        List<EatLog> eatLogs = this.queryFrom(searchSourceBuilder);
        if (CollectionUtils.isEmpty(eatLogs)) {
            return null;
        }
        return eatLogs.get(0);
    }

    public List<EatLog> queryFrom(SearchSourceBuilder searchSourceBuilder) {
        try {
            SearchRequest searchRequest = Requests.searchRequest(EAT_LOG_INDEX).source(searchSourceBuilder);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits == null || hits.length == 0) {
                return Collections.emptyList();
            }
            return Arrays.stream(hits).map(hit -> {
                EatLog stockProfit = new Gson().fromJson(hit.getSourceAsString(), EatLog.class);
                stockProfit.setId(hit.getId());
                return stockProfit;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("queryFrom error, ", e);
        }
        return Collections.emptyList();
    }


    public void update(EatLog eatLog) throws IOException {
        BulkRequest bulkRequest = Requests.bulkRequest();
        bulkRequest.add(new UpdateRequest(EAT_LOG_INDEX, eatLog.getId()).doc(new Gson().toJson(eatLog), XContentType.JSON));
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        if (bulkResponse.hasFailures()) {
            throw new RuntimeException("发现异常，请检查");
        }
    }

}
