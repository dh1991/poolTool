package com.pool.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({HttpPoolProperties.class})
public class HttpPoolAutoConfiguration {

    @Bean("poolHttpManager")
    @ConditionalOnMissingBean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(HttpPoolProperties properties) {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(properties.getMaxTotal());
        manager.setDefaultMaxPerRoute(properties.getMaxPerRoute());
        return manager;
    }

    @Bean("poolHttpConfig")
    @ConditionalOnMissingBean
    public RequestConfig requestConfig(HttpPoolProperties properties) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(properties.getConnectTimeout())
                .setSocketTimeout(properties.getSocketTimeout())
                .setConnectionRequestTimeout(properties.getConnectionRequestTimeout())
                .build();
        return requestConfig;
    }
    @Bean
    @ConditionalOnMissingBean
    public PoolHttpUtil poolHttpUtil(PoolingHttpClientConnectionManager manager,RequestConfig requestConfig){
        PoolHttpUtil httpUtil = new PoolHttpUtil(manager,requestConfig);
        return httpUtil;
    }
}

