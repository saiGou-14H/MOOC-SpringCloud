package com.org.login10000;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
@SpringBootApplication
@EnableEurekaClient     //在服务启动后自动注册到Eureka中
@EnableCircuitBreaker   //添加对服务熔断的支持
public class Login10000Application {

    public static void main(String[] args) {
        SpringApplication.run(Login10000Application.class, args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
