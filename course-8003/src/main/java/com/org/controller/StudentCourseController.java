package com.org.controller;


import com.org.model.Course;
import com.org.model.Result;
import com.org.model.StudentCourse;
import com.org.service.IStudentCourseService;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 学生课程关联表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-10-31
 */
@RestController
@RequestMapping("/studentCourse")
public class StudentCourseController {

    @Autowired
    private RestTemplate restTemplate;      //提供多种便捷访问远程http服务的方法，简单的Restful服务模板

    @Autowired
    private IStudentCourseService studentCourseService;

    private static final String REST_URL_PREFIX_DEPT = "http://DEPT-8002";

    /*
    * add
    * */
    @ApiOperation(value = "学生课程关联-批量1")
    @PostMapping("/adStuCous/{cou_id}")
    public boolean adStuCous1(@PathVariable("cou_id") long cou_id, @RequestBody List<Long> stuIds) {

        List<StudentCourse> studentCourses = new ArrayList<>();
        Iterator<Long> iterator = stuIds.iterator();//迭代
        while (iterator.hasNext()) {
            studentCourses.add(new StudentCourse().setStuId(iterator.next()).setCouId(cou_id));
        }
        if (!studentCourseService.saveBatch(studentCourses)) return false;
        return true;
    }

    @ApiOperation(value = "学生课程关联-批量2")
    @GetMapping("/adStuCous2/{cla_id}/{stu_id}")
    public boolean adStuCous2(@PathVariable("cla_id") Long cla_id, @PathVariable("stu_id") Long stu_id) {
        //查询班级关联的课程
        Result result = restTemplate.getForObject(REST_URL_PREFIX_DEPT+"/classCourse/shCourses/"+cla_id, Result.class);

        ArrayList<String> temp = (ArrayList<String>) result.getData();
        List<Long> couIds = temp.stream().map(Long::parseLong).collect(Collectors.toList());
        //如果班级有课程
        if(!couIds.isEmpty()) {
            List<StudentCourse> studentCourses = new ArrayList<>();
            Iterator<Long> iterator = couIds.iterator();//迭代
            while (iterator.hasNext()) {
                Long couid = iterator.next();
//            System.out.println(stu_id+":"+couid);
                studentCourses.add(new StudentCourse().setStuId(stu_id).setCouId(couid));
            }
            if (!studentCourseService.saveBatch(studentCourses)) return false;
        }

        return true;
    }

    /*
    * delete
    * */

    /*
     * update
     * */

    /*
     * search
     * */


}

