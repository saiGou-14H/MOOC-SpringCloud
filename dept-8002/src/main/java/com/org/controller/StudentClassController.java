package com.org.controller;


import com.org.model.Result;
import com.org.service.IStudentClassService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 学生班级关联表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@RestController
@RequestMapping("/studentClass")
public class StudentClassController {

    @Autowired
    private RestTemplate restTemplate;      //提供多种便捷访问远程http服务的方法，简单的Restful服务模板

    @Autowired
    private IStudentClassService studentClassService;

    private static final String REST_URL_PREFIX_USER = "http://USER-8001";

    /*
    * add
    * */

    /*
     *delete
     * */

    /*
     *update
     * */

    /*
     *search
     * */
    @ApiOperation(value = "查询本班级所有学生id")
    @GetMapping("/shClassStu/{cla_id}")
    public Result shClassStu(@PathVariable("cla_id") Long cla_id) {
        List<Long> stuNames = studentClassService.shClassStu(cla_id);
        return new Result().setData(stuNames);
    }

    @ApiOperation(value = "查询本班级所有学生信息")
    @GetMapping("/shClassStuMsg/{cla_id}")
    public Result shClassStuMsg(@PathVariable("cla_id") Long cla_id) {
        List<Long> stuNames = studentClassService.shClassStu(cla_id);
        Result result = restTemplate.postForObject(REST_URL_PREFIX_USER+"/user/shUserByCla", stuNames, Result.class);
        return new Result().setData(result.getData());
    }

}

