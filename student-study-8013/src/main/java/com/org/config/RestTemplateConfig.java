package com.org.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration                  //soring applicationContext.xml
public class RestTemplateConfig {

    //配置负载均衡实现RestTemplate
    @Bean
    @LoadBalanced   //Ribbon
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
