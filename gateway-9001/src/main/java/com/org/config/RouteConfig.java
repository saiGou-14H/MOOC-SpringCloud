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
                .route("dept-8002-1", r -> r.path("/class/**").uri("lb://dept-8002"))
                .route("dept-8002-2", r -> r.path("/classCourse/**").uri("lb://dept-8002"))
                .route("dept-8002-3", r -> r.path("/studentClass/**").uri("lb://dept-8002"))
                .route("course-8003-1", r -> r.path("/course/**").uri("lb://course-8003"))
                .route("course-8003-2", r -> r.path("/courseType/**").uri("lb://course-8003"))
                .route("course-8003-3", r -> r.path("/studentCourse/**").uri("lb://course-8003"))
                .route("course-8003-4", r -> r.path("/studentPractice/**").uri("lb://course-8003"))
                .route("course-8003-5", r -> r.path("/practice/**").uri("lb://course-8003"))
                .route("community-8004-1", r -> r.path("/message/**").uri("lb://community-8004"))
                .route("community-8004-2", r -> r.path("/messageComment/**").uri("lb://community-8004"))
                .build();
    }

}
