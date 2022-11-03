package com.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer     //服务端的启动类，可接受别人注册进来
public class Eureka7002Application {

    public static void main(String[] args) {
        SpringApplication.run(Eureka7002Application.class, args);
    }

}
