package com.pool.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableConfigurationProperties({ThreadPoolProperties.class})
public class ThreadPoolAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ThreadPoolExecutor poolExecutor(ThreadPoolProperties properties){
        ThreadPoolExecutor executor  = new ThreadPoolExecutor(properties.getCorePoolSize(),
                properties.getMaximumPoolSize(),
                properties.getKeepAliveTime(),properties.getTimeUnit(),
                properties.getQueue(),new ThreadFactoryBuilder().setNameFormat("threadPool-%d").build(),
                properties.getRejectedHandler());
        return executor;
    }

    @Bean
    @ConditionalOnMissingBean
    public PoolThreadUtil poolThreadUtil(ThreadPoolExecutor poolExecutor){
        PoolThreadUtil httpUtil = new PoolThreadUtil(poolExecutor);
        return httpUtil;
    }
}
