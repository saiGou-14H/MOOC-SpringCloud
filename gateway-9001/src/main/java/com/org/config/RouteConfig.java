package com.org.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-8001", r -> r.path("/user/**").uri("lb://user-8001"))
                .route("dept-8002", r -> r.path("/class/**").uri("lb://dept-8002"))
                .route("course-8003", r -> r.path("/course/**").uri("lb://course-8003"))
                .route("community-8004", r -> r.path("/community/**").uri("lb://community-8004"))
                .build();
    }

}
