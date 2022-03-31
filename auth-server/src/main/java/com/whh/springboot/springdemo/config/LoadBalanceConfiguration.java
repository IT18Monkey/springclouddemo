package com.whh.springboot.springdemo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LoadBalanceConfiguration {
    @Bean("restTemplate1")
    @LoadBalanced
    public RestTemplate restTemplate1() {
        return new RestTemplate();
    }
    @Bean("restTemplate2")
    public RestTemplate restTemplate2() {
        return new RestTemplate();
    }
}
