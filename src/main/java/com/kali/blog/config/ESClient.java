package com.kali.blog.config;

import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 链接ES的客户端
 *
 * @author Elliot
 * @date 2021/11/21 6:29 下午
 */
@Log4j2
@Configuration
public class ESClient {
    @Bean
    public RestHighLevelClient esClient() {
        log.info("初始化ES连接");
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9201, "http"),
                new HttpHost("localhost", 9300, "http")
        ));
        log.info("ES连接成功");
        return client;
    }
}
