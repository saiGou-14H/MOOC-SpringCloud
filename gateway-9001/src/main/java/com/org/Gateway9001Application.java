package com.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient     //在服务启动后自动注册到Eureka中
public class Gateway9001Application {

    public static void main(String[] args) {
        SpringApplication.run(Gateway9001Application.class, args);
    }

}
