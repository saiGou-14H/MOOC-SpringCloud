package com.org.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.org.model.*;
import com.org.model.dto.QuestionAnswerDTO1;
import com.org.model.vo.Course1;
import com.org.service.*;
import com.org.config.utils.ImageUtil;
import com.org.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private ICourseChapterService courseChapterService;
    @Autowired
    private IPracticeService practiceService;
    @Autowired
    private IStudentPracticeService studentPracticeService;
    @Autowired
    private IStudentCourseService studentCourseService;
    @Autowired
    private ILearnProgressService learnProgressService;

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
    public Result ctCourse(@RequestBody Course course, HttpServletRequest request) {

        try {
            //生成课程id
            IdGeneratorOptions options = new IdGeneratorOptions((short)1);
            YitIdHelper.setIdGenerator(options);
            Long newId = YitIdHelper.nextId();
            course.setId(newId/1000000);

            //存储课程封面
            String pic_str = ImageUtil.Base64ToPic(course);
            System.out.println(pic_str);
            course.setPicture("/picture"+pic_str);

            Long tea_id = JwtUtil.getId(request);
            course.setTeaId(tea_id);
            //插入课程封面路径
        } catch (Exception e) {e.printStackTrace();}
//        //查找是否创建过同名的课程
//        if(courseService.getOne(new QueryWrapper<Course>().eq("name",course.getName()).eq("tea_id", course.getTeaId())) != null) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "您已创建过该课程");
//
//        //最后插入数据
//        if(!courseService.save(course)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");
        return Result.success(HttpStatus.SC_OK, "True");
    }
    public Result hystrixCtCourse(@RequestBody Course course, @RequestParam Long parentId, HttpServletRequest request) {
        return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据或请求头");
    }
    @Test
    public void fun() {

        String str = "http://api.spoc.com:9001/picture/354116089_Android.jpg";
        if(str.contains("picture")) {
            System.out.println("yes");
        }
    }

    /*
     * delete
     * */
    @ApiOperation(value = "班级移除课程")
    @GetMapping("/delCourse/{class_id}/{cou_id}")
    public Result delCourse (@PathVariable("class_id") Long cla_id, @PathVariable("cou_id") Long cou_id) throws InterruptedException {
        //先查询该课程的所有实践id
        List<String> practiceIds = practiceService.shPracticeIds(cou_id);
        //先查询该班的全部学生id
        Result result2 = restTemplate.getForObject(REST_URL_PREFIX_DEPT+"/studentClass/shClassStu/"+cla_id, Result.class);
        List<Long> stuIds = (List<Long>) result2.getData();

        //如果有实践的话
        if(!practiceIds.isEmpty()) {
            List<StudentPractice> studentPractices = new ArrayList<>();
            //转为Long类型
            List<Long> praIds = practiceIds.stream().map(Long::parseLong).collect(Collectors.toList());

            Iterator<Long> iterator1 = praIds.iterator();
            //先遍历实践id
            while (iterator1.hasNext()) {
                Long praId = iterator1.next();
                //再遍历学生id
                Iterator<Long> iterator2 = stuIds.iterator();
                while (iterator2.hasNext()) {        //先遍历实践id
                    //然后删除学生实践关联
                    try {
                        studentPracticeService.remove(new QueryWrapper<StudentPractice>().eq("pra_id", praId).eq("stu_id", iterator2.next()));
                    }catch (Exception e){}
                    Thread.sleep(5);
                }
            }
            //删除实践
            practiceService.removeBatchByIds(praIds);
        }
        //再将学生与课程解除关联
        //再遍历学生id
        Iterator<Long> iterator3 = stuIds.iterator();
        while (iterator3.hasNext()) {        //先遍历实践id
            //然后删除学生实践关联
            try {
                studentCourseService.remove(new QueryWrapper<StudentCourse>().eq("cou_id", cou_id).eq("stu_id", iterator3.next()));
            }catch (Exception e){}
            Thread.sleep(1);
        }

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("cla_id", cla_id);
        requestMap.put("cou_id", cou_id);
        //最后将课程从班级移除
        Result result3 = restTemplate.getForObject(REST_URL_PREFIX_DEPT+"/classCourse/delClaCou/{cla_id}/{cou_id}",Result.class,requestMap);
        if (result3.getCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");}
        return Result.success(HttpStatus.SC_OK, "True");
    }
    @ApiOperation(value = "删除课程")
    @PostMapping("/delCourses")
    public Result delCourses(@RequestBody String data) throws InterruptedException {
        JSONObject jsonObject =  JSON.parseObject(data);
        List<String> couIds_str = (List<String>) jsonObject.get("couIds");
        List<Long> couIds = couIds_str.stream().map(Long::parseLong).collect(Collectors.toList());

        Iterator<Long> iterator1 = couIds.iterator();
        //遍历课程
        while (iterator1.hasNext()) {
            //课程id
            Long cou_id = iterator1.next();
            //查课程下所有实践的id
            List<String> praIds_str =  practiceService.shPracticeIds(cou_id);
            //如果不为空
            if (!praIds_str.isEmpty()) {
                //先将实践id类型转换
                List<Long> praIds = praIds_str.stream().map(Long::parseLong).collect(Collectors.toList());
                Iterator<Long> iterator2 = praIds.iterator();
                //遍历实践
                while (iterator2.hasNext()) {
                    Long pra_id = iterator2.next();
                    try {
                        //删除学生实践关联
                        studentPracticeService.remove(new QueryWrapper<StudentPractice>().eq("pra_id", pra_id));
                    } catch (Exception e){}
                    Thread.sleep(10);
                }
            }
            try {
                //删除实践
                practiceService.remove(new QueryWrapper<Practice>().eq("cou_id", cou_id));
            } catch (Exception e){}
            try {
                //删除学生学习进度
                learnProgressService.remove(new QueryWrapper<LearnProgress>().eq("cou_id", cou_id));
            } catch (Exception e){}
            try {
                //删除课程章节
                courseChapterService.remove(new QueryWrapper<CourseChapter>().eq("course_id", cou_id));
            } catch (Exception e){}
            try {
                //删除学生与课程关联
                studentCourseService.remove(new QueryWrapper<StudentCourse>().eq("cou_id", cou_id));
            } catch (Exception e){}
            //删除班级课程关联
            try {
                restTemplate.getForObject(REST_URL_PREFIX_DEPT+"/classCourse/delClasCou/"+cou_id, Result.class).getCode();
            } catch (Exception e){}
            //最后删除这个课程
            courseService.remove(new QueryWrapper<Course>().eq("id", cou_id));
        }

        return Result.success(HttpStatus.SC_OK, "True");
    }

    /*
     * update
     * */
    @ApiOperation(value = "修改课程")
    @PostMapping("/udCourse")
    public Result searchOne(@RequestBody Course course) {
        //存储课程封面
        String pic_str = ImageUtil.Base64ToPic(course);
        System.out.println(pic_str);
        course.setPicture("/picture"+pic_str);
        //更新数据
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

    @ApiOperation(value = "管理员-查询课程的问题及回复")
    @GetMapping("/shQueAns/{cla_id}/{cou_id}/{isCheck}")
    public Result shQueAns(@PathVariable("cla_id") Long claId, @PathVariable("cou_id") Long couId, @PathVariable("isCheck") Long isCheck) {
        Map<String, Long> map = new HashMap<>();
        map.put("claId", claId);
        map.put("couId", couId);
        map.put("isCheck", isCheck);
        List<QuestionAnswerDTO1> questionAnswers = courseService.shQueAns(map);
        return Result.success(HttpStatus.SC_OK, "true", questionAnswers);
    }
    @ApiOperation(value = "教师-查询课程的问题及回复")
    @GetMapping("/shQueAns2/{cla_id}/{cou_id}/{isCheck}")
    public Result shQueAns2(@PathVariable("cla_id") Long claId, @PathVariable("cou_id") Long couId, @PathVariable("isCheck") Long isCheck) {
        Map<String, Long> map = new HashMap<>();
        map.put("claId", claId);
        map.put("couId", couId);
        map.put("isCheck", isCheck);
        List<QuestionAnswerDTO1> questionAnswers = courseService.shQueAns2(map);
        return Result.success(HttpStatus.SC_OK, "true", questionAnswers);
    }

    @ApiOperation(value = "查询教师全部课程")
    @GetMapping("/shAllCourse")
    public Result shAllCourse(HttpServletRequest request) {
        List<Course> courseList = courseService.list(new QueryWrapper<Course>().eq("tea_id", JwtUtil.getId(request)));
        return Result.success(HttpStatus.SC_OK, "null", courseList);
    }

    @ApiOperation(value = "查询所有章节")
    @GetMapping("/shChapter/{cou_id}")
    public Result shChapter(@PathVariable("cou_id") Long cou_id) {
        //查询该课程所有章节
        List<CourseChapter> courseChapterList = courseChapterService.list(new QueryWrapper<CourseChapter>().eq("course_id", cou_id));
        //按章节序号进行排序
        courseChapterList.sort(Comparator.comparing(CourseChapter::getChaIndex));
        return Result.success(HttpStatus.SC_OK, "ture", courseChapterList);
    }



}

