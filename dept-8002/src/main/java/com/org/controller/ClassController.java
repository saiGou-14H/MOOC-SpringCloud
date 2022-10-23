package com.org.controller;


import com.org.model.Class;
import com.org.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 班级表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private IClassService classService;

    private static final String REST_URL_PREFIX_User = "http://USER-8001";
    private static final String REST_URL_PREFIX_COURSE = "http://COURSE-8003";
    private static final String REST_URL_PREFIX_COMMUNITY = "http://COMMUNITY-8004";

    @GetMapping("/searchAllClass")
    public List<Class> searchAllClass() {
        return classService.list();
    }

}

