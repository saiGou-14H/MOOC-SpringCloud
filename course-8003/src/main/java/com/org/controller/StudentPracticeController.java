package com.org.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.model.*;
import com.org.service.ILearnProgressService;
import com.org.service.IPracticeService;
import com.org.service.IStudentCourseService;
import com.org.service.IStudentPracticeService;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 学生实践关联表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-10-28
 */
@RestController
@RequestMapping("/studentPractice")
public class StudentPracticeController {

    @Autowired
    private RestTemplate restTemplate;      //提供多种便捷访问远程http服务的方法，简单的Restful服务模板

    @Autowired
    private IStudentCourseService studentCourseService;
    @Autowired
    private IStudentPracticeService studentPracticeService;
    @Autowired
    private IPracticeService practiceService;
    @Autowired
    private ILearnProgressService learnProgressService;

    private static final String REST_URL_PREFIX_DEPT = "http://DEPT-8002";

    /*
    * add
    * */
    @ApiOperation(value = "插入学生与实践关联-批量1")
    @GetMapping("/adStuPractice/{pra_id}/{cla_id}/{cou_id}")
    public boolean adStuPractice1(@PathVariable("pra_id") Long pra_id, @PathVariable("cla_id") Long cla_id, @PathVariable("cou_id") Long cou_id) {
        //查询该班级所有学生
        Result result = restTemplate.getForObject(REST_URL_PREFIX_DEPT + "/studentClass/shClassStu/" + cla_id, Result.class);
        List<Long> stuNames = (List<Long>) result.getData();
        System.out.println(stuNames);
        List<StudentPractice> studentPracticeList = new ArrayList<>();
        //迭代
        Iterator<Long> iterator = stuNames.iterator();
        while (iterator.hasNext()) {
            studentPracticeList.add(new StudentPractice().setStuId(iterator.next()).setPraId(pra_id)
                    .setPraDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        }
        if (!studentPracticeService.saveBatch(studentPracticeList)) return false;
//        //迭代
//        System.out.println("有了"+studentPracticeList);
//        Iterator<StudentPractice> iterator1 = studentPracticeList.iterator();
//        while (iterator1.hasNext()) {
//            System.out.println(iterator1.next());
//        }
        return true;
    }

    @ApiOperation(value = "插入学生与实践关联-批量2")
    @GetMapping("/adStuPra/{cla_id}/{stu_id}")
    public boolean adStuPractice2(@PathVariable("cla_id") Long cla_id, @PathVariable("stu_id") Long stu_id) {

        //查询实践id
        Result result = restTemplate.getForObject(REST_URL_PREFIX_DEPT+"/classCourse/shCoursesPra/"+cla_id, Result.class);

        ArrayList<String> temp = (ArrayList<String>) result.getData();
        List<Long> praIds = temp.stream().map(Long::parseLong).collect(Collectors.toList());
        //如果班级有实践
        if(!praIds.isEmpty()) {
            List<StudentPractice> studentPracticeList = new ArrayList<>();
            Iterator<Long> iterator = praIds.iterator();//迭代
            while (iterator.hasNext()) {
                studentPracticeList.add(new StudentPractice().setStuId(stu_id).setPraId(iterator.next())
                        .setPraDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            }
            if (!studentPracticeService.saveBatch(studentPracticeList)) return false;
        }

        return true;
    }

    /*
    * delete
    * */
    @ApiOperation(value = "删除学生与各课程实践")
    @PostMapping("/delStuPra/{stu_id}")
    public Result delStuPra(@PathVariable Long stu_id, @RequestBody List<String> couIds) throws InterruptedException {
        //迭代每门课程
        Iterator<String> it1 = couIds.iterator();
        while(it1.hasNext()) {
            Long course = Long.parseLong(it1.next());
            //查找每门课程的所有实践
            List<String> praIds = practiceService.shPracticeIds(course);
            //如果有实践就遍历删除
            if(!praIds.isEmpty()) {
                Iterator<String> it2 = praIds.iterator();
                //迭代每个实践
                while(it2.hasNext()) {
                    Long practice = Long.parseLong(it2.next());
                    //一个个封装成对象然后删除
                    studentPracticeService.remove(new QueryWrapper<StudentPractice>().eq("stu_id", stu_id).eq("pra_id", practice));
                    Thread.sleep(10);
                }
            }
            //再删除学生的学习进度
            try {learnProgressService.remove(new QueryWrapper<LearnProgress>().eq("stu_id", stu_id).eq("cou_id", course));}catch (Exception e) {}
            //最后删除学生与课程实践关联
            studentCourseService.remove(new QueryWrapper<StudentCourse>().eq("stu_id", stu_id).eq("cou_id", course));
        }
        return Result.success(HttpStatus.SC_OK, "true");
    }


    /*
     * update
     * */

    /*
     * search
     * */

}

