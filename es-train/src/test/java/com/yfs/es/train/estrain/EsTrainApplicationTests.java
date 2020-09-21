package com.yfs.es.train.estrain;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class EsTrainApplicationTests {

	@Qualifier("restHighLevelClient")
	@Autowired
	private RestHighLevelClient client;


 	@Test
	void testCreateIndex() throws IOException {
// 1、创建索引请求
//		CreateIndexRequest infoIndex = new CreateIndexRequest("ths_stock_info"); // 2、客户端执行请求 IndicesClient,请求后获得响应
		CreateIndexRequest priceIndex = new CreateIndexRequest("ths_stock_price");
//		CreateIndexResponse createIndexResponse = client.indices().create(infoIndex, RequestOptions.DEFAULT);
		CreateIndexResponse createPriceIndexResponse = client.indices().create(priceIndex, RequestOptions.DEFAULT);
		System.out.println(createPriceIndexResponse);
	}

	@Test
	void testExistIndex() throws IOException {
		GetIndexRequest request = new GetIndexRequest("ths_stock_price");
		boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);
 	}
	// 测试删除索引
	@Test
	void testDeleteIndex() throws IOException {
		DeleteIndexRequest request = new DeleteIndexRequest("ths_stock_price");
		AcknowledgedResponse delete = client.indices().delete(request,
				RequestOptions.DEFAULT); System.out.println(delete.isAcknowledged());
	}




}
