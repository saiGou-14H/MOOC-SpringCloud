package com.org.controller;


import com.org.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    private static final String REST_URL_PREFIX_User = "http://USER-8001";
    private static final String REST_URL_PREFIX_DEPT = "http://DEPT-8002";
    private static final String REST_URL_PREFIX_COMMUNITY = "http://COMMUNITY-8004";

}

