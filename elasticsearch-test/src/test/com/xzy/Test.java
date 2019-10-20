package com.xzy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzy.search.HouseIndexKey;
import com.xzy.search.HouseIndexTemplate;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class Test {
    private TransportClient client;

    private static final String INDEX_NAME = "xunwu";
    private static final String INDEX_TYPE = "house";

    @Before
    public void before() throws UnknownHostException {
        Settings setting = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", true)
                .build();
        client = new PreBuiltTransportClient(setting)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.135.131"), 9300));
    }

    @org.junit.Test
    public void create() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HouseIndexTemplate houseIndexTemplate = new HouseIndexTemplate();
        houseIndexTemplate.setHouseId(1L);
        houseIndexTemplate.setArea("1");
        houseIndexTemplate.setCityEnName("北京市");
        houseIndexTemplate.setCreateTime(new Date());
        houseIndexTemplate.setDescription("描述一下");
        houseIndexTemplate.setLastUpdateTime(new Date());
        houseIndexTemplate.setSubwayLineName("sub");
        IndexResponse response = client.prepareIndex(INDEX_NAME, INDEX_TYPE)
                .setSource(mapper.writeValueAsBytes(houseIndexTemplate), XContentType.JSON)
                .get();
        if (response.status() == RestStatus.CREATED) {
            System.out.println("创建成功");
        }
    }

    @org.junit.Test
    public void update() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HouseIndexTemplate houseIndexTemplate = new HouseIndexTemplate();
        houseIndexTemplate.setDistanceToSubway("1");
        houseIndexTemplate.setDistrict("描述一下自己吧");
        UpdateResponse response = client.prepareUpdate(INDEX_NAME, INDEX_TYPE, "gkHKj20BSTKdKJZ2CEr8")
                .setDoc(mapper.writeValueAsBytes(houseIndexTemplate), XContentType.JSON)
                .get();
        if (response.status() == RestStatus.OK) {
            System.out.println("更新成功");
        }
    }

    @org.junit.Test
    public void delete() {
        HouseIndexTemplate houseIndexTemplate = new HouseIndexTemplate();
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, houseIndexTemplate.getHouseId()))
                .source(INDEX_NAME);
        BulkByScrollResponse response = builder.get();
        long deleted = response.getDeleted();
        System.out.println("删除个数deleted = " + deleted);

    }


    @org.junit.Test
    public void remove() {

    }

}
