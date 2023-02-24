package com.org.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.org.model.Class;
import com.org.model.ClassCourse;
import com.org.model.Result;
import com.org.service.IClassCourseService;
import com.org.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 班级课程关联表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@RestController
@RequestMapping("/classCourse")
public class ClassCourseController {

    @Autowired
    private RestTemplate restTemplate;      //提供多种便捷访问远程http服务的方法，简单的Restful服务模板

    @Autowired
    private IClassCourseService classCourseService;

    private static final String REST_URL_PREFIX_DEPT = "http://DEPT-8002";
    private static final String REST_URL_PREFIX_COURSE = "http://COURSE-8003";

//    @ApiOperation(value = "班级添加课程")
//    @GetMapping("/adClassCourse/{class_id}/{cou_id}")
//    @HystrixCommand(fallbackMethod = "hystrixAdClassCourse")
//    public Result adClassCourse(@PathVariable("class_id") Long class_id, @PathVariable("cou_id") Long cou_id) {
//
//        ClassCourse classCourse = new ClassCourse();
//        try {
//            classCourse.setClassId(class_id).setCouId(cou_id);
//        } catch (Exception e) {}
//
//        if(!classCourseService.save(classCourse)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");
//        return Result.success(HttpStatus.SC_OK, "True");
//    }
    /*
    * add
    * */
    @ApiOperation(value = "班级添加课程")
    @GetMapping("/adCourse/{class_id}/{cou_id}")
    //@HystrixCommand(fallbackMethod = "hystrixAdCourse")                    //
    public Result adCourse(@PathVariable("class_id") Long class_id, @PathVariable("cou_id") Long cou_id) {
        ClassCourse classCourse = new ClassCourse();
        //首先检查数据是否符合条件
        try {classCourse.setClassId(class_id).setCouId(cou_id);} catch (Exception e) {}
        //再检查该课程是否加入班级
        if(classCourseService.getOne(new QueryWrapper<ClassCourse>().eq("class_id", class_id).eq("cou_id", cou_id)) != null) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "该班级已存在此课程");
        //在插入数据的同时坚持是否插入成功
        if(!classCourseService.save(classCourse)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");
        //将班级的全部学生与此课程进行关联
        //先将学生id查出来
        Result result = restTemplate.getForObject(REST_URL_PREFIX_DEPT+"/studentClass/shClassStu/"+class_id, Result.class);
        System.out.println(result.getData());
        if(result.getData() != null) {          //若班级中已有学生
            ResponseEntity<Boolean> flag = restTemplate.postForEntity(REST_URL_PREFIX_COURSE+"/studentCourse/adStuCous/"+cou_id, (List<Long>)result.getData(), boolean.class);
            if(!flag.getBody()) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "学生与课程关联出错");
        }

        return Result.success(HttpStatus.SC_OK, "True");
    }
    public Result hystrixAdCourse(@PathVariable("class_id") Long class_id, @PathVariable("cou_id") Long cou_id) {
        return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据或请求头");
    }

    public Result hystrixAdClassCourse(@PathVariable("class_id") Long class_id, @PathVariable("cou_id") Long cou_id) {
        return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据或请求头");
    }

    /*
    * deleted
    * */
    @ApiOperation(value = "删除班级课程关联")
    @GetMapping("/delClaCou/{cla_id}/{cou_id}")
    public Result delClaCou(@PathVariable("cla_id") Long cla_id, @PathVariable("cou_id") Long cou_id) {

        boolean flag = classCourseService.remove(new QueryWrapper<ClassCourse>().eq("class_id",cla_id).eq("cou_id", cou_id));
        if(!flag) {return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");}
        return Result.success(HttpStatus.SC_OK, "True");
    }
    @ApiOperation(value = "删除课程与所有班级的关联")
    @GetMapping("/delClasCou/{cou_id}")
    public Result delClasCou(@PathVariable("cou_id") Long cou_id) {

        boolean flag = classCourseService.remove(new QueryWrapper<ClassCourse>().eq("cou_id", cou_id));
        if(!flag) {return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");}
        return Result.success(HttpStatus.SC_OK, "True");
    }


    /*
    * update
    * */

    /*
    * search
    * */
    @ApiOperation(value = "查询班级所有关联的课程id")
    @GetMapping("/shCourses/{cla_id}")
    public Result shCourses(@PathVariable("cla_id") Long cla_id) {
        List<String> couIds = classCourseService.shCourses(cla_id);
        return Result.success(HttpStatus.SC_OK, "null", couIds);
    }

    @ApiOperation(value = "查询班级关联的课程的实践id")
    @GetMapping("/shCoursesPra/{cla_id}")
    public Result shCoursesPra(@PathVariable("cla_id") long cla_id) {
        List<Long> praIds = classCourseService.shCoursesPra(cla_id);
        return Result.success(HttpStatus.SC_OK, "null", praIds);
    }

}

