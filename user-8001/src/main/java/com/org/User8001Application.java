package com.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient     //在服务启动后自动注册到Eureka中
@EnableCircuitBreaker   //添加对服务熔断的支持
//@EnableScheduling //开启定时任务
public class User8001Application {

    public static void main(String[] args) {
        SpringApplication.run(User8001Application.class, args);
    }

}
