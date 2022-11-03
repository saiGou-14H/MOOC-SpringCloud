package com.org.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.org.model.Class;
import com.org.model.Course;
import com.org.model.Result;
import com.org.model.vo.Course1;
import com.org.service.ICourseService;
import com.org.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private RestTemplate restTemplate;      //提供多种便捷访问远程http服务的方法，简单的Restful服务模板
    @Autowired
    private ICourseService courseService;

    private static final String REST_URL_PREFIX_User = "http://USER-8001";
    private static final String REST_URL_PREFIX_DEPT = "http://DEPT-8002";
    private static final String REST_URL_PREFIX_COURSE = "http://COURSE-8003";
    private static final String REST_URL_PREFIX_COMMUNITY = "http://COMMUNITY-8004";

    /*
    * add
    * */
    @ApiOperation(value = "创建课程")
    @PostMapping("/ctCourse")
    //@HystrixCommand(fallbackMethod = "hystrixCtCourse")
    public Result ctCourse(@RequestBody Course course, @RequestParam Long parentId, HttpServletRequest request) {
        try {
            IdGeneratorOptions options = new IdGeneratorOptions((short)1);
            YitIdHelper.setIdGenerator(options);
            Long newId = YitIdHelper.nextId();
            course.setId(newId/1000000);

            Long tea_id = JwtUtil.getId(request);
            course.setTeaId(tea_id);
        } catch (Exception e) {e.printStackTrace();}
        //查找是否创建过同名的课程
        if(courseService.getOne(new QueryWrapper<Course>().eq("name",course.getName()).eq("tea_id", course.getTeaId())) != null) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "您已创建过该课程");

        //创建关联
        Map<String, Object> requestmap = new HashMap<>();
        requestmap.put("id", course.getId());
        requestmap.put("type", course.getCouType());
        requestmap.put("parent_id", parentId);
        System.out.println("传值前检查"+requestmap);
        //先插入关联
        if(!restTemplate.getForObject(REST_URL_PREFIX_COURSE+"/courseType/adCourseType/{id}/{type}/{parent_id}", boolean.class, requestmap)) throw new RuntimeException("插入课程字典发生错误");
        //最后插入数据
        if(!courseService.save(course)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");
        return Result.success(HttpStatus.SC_OK, "True");
    }
    public Result hystrixCtCourse(@RequestBody Course course, @RequestParam Long parentId, HttpServletRequest request) {
        return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据或请求头");
    }

    /*
     * delete
     * */

    /*
     * update
     * */
    @ApiOperation(value = "修改课程")
    @PostMapping("/udCourse")
    public Result searchOne(@RequestBody Course course) {
        if(!courseService.udCourse(course)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR,"请检查参数");
        return Result.success(HttpStatus.SC_OK,"null");
    }


    /*
     * search
     * */
    @ApiOperation(value = "查询课程")
    @GetMapping("/shCourse")
    public Result searchOne(@RequestBody Course1 course1) {
        List<Course1> courseList = courseService.shCourse(course1);
        return Result.success(HttpStatus.SC_OK, "null", courseList);
    }

}

