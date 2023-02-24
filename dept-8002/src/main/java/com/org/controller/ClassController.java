package com.org.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.org.config.utils.FileUtil;
import com.org.model.*;
import com.org.model.Class;
import com.org.model.dto.ClassDTO1;
import com.org.model.dto.UserDTO2;
import com.org.service.IClassCourseService;
import com.org.service.IClassService;
import com.org.service.IStudentClassService;
import com.org.util.JwtUtil;
import com.org.util.SensitiveWordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 班级表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@Api(value = "班级接口")
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private RestTemplate restTemplate;      //提供多种便捷访问远程http服务的方法，简单的Restful服务模板

    @Autowired
    private IClassService classService;
    @Autowired
    private IStudentClassService studentClassService;
    @Autowired
    private IClassCourseService classCourseService;

    private static final String REST_URL_PREFIX_USER = "http://USER-8001";
    private static final String REST_URL_PREFIX_DEPT = "http://DEPT-8002";
    private static final String REST_URL_PREFIX_COURSE = "http://COURSE-8003";
    private static final String REST_URL_PREFIX_COMMUNITY = "http://COMMUNITY-8004";

    /*
    * add
    * */
    @ApiOperation(value = "创建班级")
    @PostMapping("/ctClass")
    @HystrixCommand(fallbackMethod = "hystrixCtClass")                    //
    public Result ctClass(@RequestBody Class classData, HttpServletRequest request) {
        try {
            IdGeneratorOptions options = new IdGeneratorOptions((short)1);
            YitIdHelper.setIdGenerator(options);
            long newId = YitIdHelper.nextId();
            classData.setId(newId/1000000);

            Long tea_id = JwtUtil.getId(request);
            classData.setTeaId(tea_id);
        } catch (Exception e) {}

        if(!classService.save(classData)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");
        return Result.success(HttpStatus.SC_OK, "True");
    }
    public Result hystrixCtClass(@RequestBody Class classData, HttpServletRequest request) {
        return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据或请求头");
    }

    @ApiOperation(value = "班级添加学生")
    @GetMapping("/adStudent/{class_id}/{stu_id}")
    //@HystrixCommand(fallbackMethod = "hystrixCtStudent")
    public Result ctStudent(@PathVariable("class_id") long class_id, @PathVariable("stu_id") long stu_id ) {
        System.out.println(class_id+":"+stu_id);
        StudentClass studentClass = new StudentClass();
        //首先检查数据是否符合条件
        try {studentClass.setClassId(class_id).setStuId(stu_id);} catch (Exception e) {}
        //再检查该学生是否加入班级
        if(studentClassService.getOne(new QueryWrapper<StudentClass>().eq("class_id", class_id).eq("stu_id", stu_id)) != null) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "该学生已加入本班级");
        //在插入数据的同时检查是否插入成功
        if(!studentClassService.save(studentClass)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");

        Map<String, Object> requestmap = new HashMap<>();
        requestmap.put("cla_id", class_id);
        requestmap.put("stu_id", stu_id);
        try {
            //插入学生与本班课程关联
            restTemplate.getForObject(REST_URL_PREFIX_COURSE+"/studentCourse/adStuCous2/{cla_id}/{stu_id}", boolean.class, requestmap);
            //插入学生与本班实践关联
            restTemplate.getForObject(REST_URL_PREFIX_COURSE+"/studentPractice/adStuPra/{cla_id}/{stu_id}", boolean.class, requestmap);
        }catch (Exception e) {}

        //修改班级人数
        long numbers = studentClassService.count(new QueryWrapper<StudentClass>().eq("class_id", class_id));
        classService.updateCourse(new Class().setId(class_id).setNumbers((int) numbers));

        return Result.success(HttpStatus.SC_OK, "True");
    }
    public Result hystrixAdStudent(@PathVariable("class_id") long class_id, @PathVariable("stu_id") long stu_id) {
        return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据或请求头");
    }

    /*
     *delete
     * */
    @ApiOperation(value = "移除学生")
    @PostMapping("/delStuFromClass/{cla_id}/{stu_id}")
    public Result delStuFromClass(@PathVariable Long cla_id, @PathVariable Long stu_id) {
        //查询班级所有关联的课程id
        List<String> couIds = classCourseService.shCourses(cla_id);
        //如果课程不为空
        if(!couIds.isEmpty()) {
            //删除学生与课程实践关联
            Result r1 = restTemplate.postForObject(REST_URL_PREFIX_COURSE + "/studentPractice/delStuPra/" + stu_id, couIds, Result.class);
            if(r1.getCode() != HttpStatus.SC_OK) {
                return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "删除学生与课程实践关联失败");
            }
        }
        //否则就直接移除这个学生
        if(!studentClassService.remove(new QueryWrapper<StudentClass>().eq("class_id", cla_id).eq("stu_id", stu_id))) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "移除失败");

        //修改班级人数
        long numbers = studentClassService.count(new QueryWrapper<StudentClass>().eq("class_id", cla_id));
        classService.updateCourse(new Class().setId(cla_id).setNumbers((int) numbers));

        return Result.success(HttpStatus.SC_OK, "true");
    }


    /*
     *update
     * */
    @ApiOperation(value = "修改班级")
    @PostMapping("/upClass")
    //@HystrixCommand(fallbackMethod = "hystrixUpClass")                    //
    public Result upClass(@RequestBody Class classData, HttpServletRequest request) {

        if(!classService.updateCourse(classData)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");
        return Result.success(HttpStatus.SC_OK, "True");
    }
    public Result hystrixUpClass(@RequestBody Class classData, HttpServletRequest request) {
        return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据或请求头");
    }

    /*
    * search
    * */
    @ApiOperation(value =  "查询班级")
    @GetMapping("/shClass/{class_id}")
    public Result shClass(@PathVariable Long class_id) {
        List<Class> classList = classService.shClass(class_id);
        if(classList==null) return Result.success(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        return Result.success(HttpStatus.SC_OK, null, classList);
    }

    @ApiOperation(value =  "查询班级所有学生")
    @GetMapping("/shStudent/{class_id}")
    public Result shStudent(@PathVariable Long class_id) {
//        Result result = restTemplate.getForObject(REST_URL_PREFIX_DEPT+"/studentClass/shClassStuMsg/"+class_id, Result.class);
//        List<User> userList = (List<User>) result.getData();
//        if(userList==null) return Result.success(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        Result result = restTemplate.getForObject(REST_URL_PREFIX_USER+"/user/shUserByCla2/"+class_id, Result.class);
        return Result.success(HttpStatus.SC_OK, null, result.getData());
    }

    @GetMapping("/searchOne/{id}/{pasword}")
    public Class searchOne(@PathVariable("id") String id, @PathVariable("pasword") String pasword) {
        System.out.println(id+"-"+pasword);
        Class classe = new Class().setName("测试").setNumbers(38);
        return classe;
    }

    @ApiOperation(value =  "查询所有班级")
    @GetMapping("/searchAllClass")
    public Result searchAllClass(HttpServletRequest request) {
        List<Class> classList = classService.searchAllClass(JwtUtil.getId(request));
        if(classList==null) return Result.success(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        return Result.success(HttpStatus.SC_OK, null, classList);
    }

    @ApiOperation("查询班级的问题及回复-模糊查询")
    @PostMapping("/shQueCom")
    public Result shQueCom(@RequestBody String data, HttpServletRequest request) {

        JSONObject jsonObject =  JSON.parseObject(data);
        Long claId = Long.parseLong(String.valueOf(jsonObject.get("claId")));

        List<ClassDTO1> classList = null;
        try {
            Map<String, Long> map = new HashMap<>();
            map.put("teaId", JwtUtil.getId(request));
            map.put("claId", claId);
            classList = classService.shQueCom(map);
        }catch (Exception e) {
            return Result.success(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false", classList);
        }

        return Result.success(HttpStatus.SC_OK, "true", classList);
    }

//    @Test
//    public void fun() throws FileNotFoundException {
//
//        String str = "TMD坤坤回民吃猪肉";
//
//
//        //加载敏感词
//        Set<String> senstiveSet = FileUtil.getFileContent();
//        SensitiveWordUtil.init(senstiveSet);
//        //查找是否有敏感词
//        boolean flag = SensitiveWordUtil.contains(str, SensitiveWordUtil.MinMatchTYpe);
//        if(flag) {
//            //获取语句中的敏感词
//            Set<String> set = SensitiveWordUtil.getSensitiveWord(str);
//            System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
//            return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "语句中包含敏感词的个数为：" + set.size(), set);
//        }
//
//    }

}

