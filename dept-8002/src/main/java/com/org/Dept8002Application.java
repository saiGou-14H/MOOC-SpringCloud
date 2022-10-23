package com.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient     //在服务启动后自动注册到Eureka中
@EnableCircuitBreaker   //添加对服务熔断的支持
public class Dept8002Application {

    public static void main(String[] args) {
        SpringApplication.run(Dept8002Application.class, args);
    }

}
