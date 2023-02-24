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
                .route("user-8002", r -> r.path("/head/**").uri("lb://user-8001"))
                .route("user-8003", r -> r.path("/file/**").uri("lb://user-8001"))
                .route("dept-8002-1", r -> r.path("/class/**").uri("lb://dept-8002"))
                .route("dept-8002-2", r -> r.path("/classCourse/**").uri("lb://dept-8002"))
                .route("dept-8002-3", r -> r.path("/studentClass/**").uri("lb://dept-8002"))
                .route("course-8003-1", r -> r.path("/course/**").uri("lb://course-8003"))
                .route("course-8003-2", r -> r.path("/courseType/**").uri("lb://course-8003"))
                .route("course-8003-3", r -> r.path("/studentCourse/**").uri("lb://course-8003"))
                .route("course-8003-4", r -> r.path("/studentPractice/**").uri("lb://course-8003"))
                .route("course-8003-5", r -> r.path("/practice/**").uri("lb://course-8003"))
                .route("course-8003-6", r -> r.path("/courseChapter/**").uri("lb://course-8003"))
                .route("course-8003-7", r -> r.path("/video/**").uri("lb://course-8003"))
                .route("course-8003-8", r -> r.path("/picture/**").uri("lb://course-8003"))
                .route("community-8004-1", r -> r.path("/message/**").uri("lb://community-8004"))
                .route("community-8004-2", r -> r.path("/messageComment/**").uri("lb://community-8004"))
                .route("community-8004-3", r -> r.path("/questionAnswer/**").uri("lb://community-8004"))
                .route("community-8004-4", r -> r.path("/questionComment/**").uri("lb://community-8004"))
                .route("student-study-8013", r -> r.path("/app/**").uri("lb://student-study-8013"))
                .route("student-study-8013", r -> r.path("/App/**").uri("lb://student-study-8013"))
                .route("student-study-8013", r -> r.path("/run/**").uri("lb://student-study-8013"))
                .route("student-find-8089", r -> r.path("/find/**").uri("lb://student-find-8089"))
                .route("community-8004-5", r -> r.path("/sensitive/**").uri("lb://community-8004"))
                .build();
    }

}
