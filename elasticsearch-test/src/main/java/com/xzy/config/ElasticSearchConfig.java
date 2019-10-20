package com.xzy.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticSearchConfig {

    public TransportClient esClient() throws UnknownHostException {
        Settings setting = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", true)
                .build();
        TransportClient client = new PreBuiltTransportClient(setting)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.135.131"), 9300));
        return client;
    }

}
