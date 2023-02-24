package com.org.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.model.Course;
import com.org.model.CourseChapter;
import com.org.model.LearnProgress;
import com.org.model.Result;
import com.org.model.vo.CourseChapterVO1;
import com.org.service.ICourseChapterService;
import com.org.service.ICourseService;
import com.org.service.ILearnProgressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 章节表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-11-05
 */
@Api("章节")
@RestController
@RequestMapping("/courseChapter")
public class CourseChapterController {

    @Autowired
    private ICourseChapterService courseChapterService;
    @Autowired
    private ILearnProgressService learnProgressService;
    @Autowired
    private ICourseService courseService;

    /*
     *add
     */
    @ApiOperation(value = "创建章节")
    @PostMapping("/ctChapter")
    public Result ctChapter(@RequestBody CourseChapter courseChapter) {
        if(!courseChapterService.insertChapter(courseChapter)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        return Result.success(HttpStatus.SC_OK, "ture");
    }
    @ApiOperation(value = "添加章节视频")
    @PostMapping("/adVideo/{cha_id}/{time}")
    public Result adVideo(@RequestParam("video") MultipartFile file, @PathVariable Integer cha_id, @PathVariable Long time) throws Exception{
        //获取根目录
        File path = new File(ResourceUtils.getURL("").getPath());//加了classpath:的话就会多出\target\classes
        if(!path.exists()) path = new File("");
        //获取文件名
        String fileName = file.getOriginalFilename();
        String src = "/course-8003/src/main/resources/static/video";
        //文件保存路径
        File realPath = new File(path.getAbsolutePath(),src);
        if(!realPath.exists()) {realPath.mkdirs();}
        File ss = new File(realPath +"/"+ fileName);
        file.transferTo(ss);
        System.out.println("文件保存路径："+realPath);
        System.out.println("文件保存完整路径："+realPath +"/"+ fileName);
        //更新数据库
        System.out.println(fileName);
        courseChapterService.udChapter(new CourseChapter().setId(cha_id).setResourceUrl("/video/"+fileName).setTime(time));
//        Thread.sleep(1000);
        return Result.success(HttpStatus.SC_OK, "ture");
    }

//    @Test
//    public void fun() throws FileNotFoundException {
//        File path = new File(ResourceUtils.getURL("").getPath());//F:\综合实训\mooc-sc\course-8003
//        System.out.println(path);
//    }

    /*
     *delete
     */
    @ApiOperation(value = "删除章节")
    @GetMapping("/delChapter/{cha_id}")
    public Result delChapter(@PathVariable Integer cha_id) {
        //先删除学生学习进度表里数据
        try {
            learnProgressService.remove(new QueryWrapper<LearnProgress>().eq("cha_id", cha_id));
        } catch (Exception e) {}
        //再删除章节视频
        try {
            adVideo(cha_id);
        } catch (Exception e) {}
        //最后再删除章节
        courseChapterService.remove(new QueryWrapper<CourseChapter>().eq("id", cha_id));
        return Result.success(HttpStatus.SC_OK, "ture");
    }

    @ApiOperation(value = "删除章节视频")
    @GetMapping("/delVideo/{cha_id}")
    public Result adVideo(@PathVariable Integer cha_id) throws Exception{
        //先查找章节信息
        CourseChapter courseChapter = courseChapterService.getOne(new QueryWrapper<CourseChapter>().eq("id", cha_id));
        File path = new File(ResourceUtils.getURL("").getPath());
        File file = new File(path+courseChapter.getResourceUrl());
        System.out.println(path+courseChapter.getResourceUrl());
        //再将章节的资源地址抹掉
        courseChapterService.udChapter(new CourseChapter().setResourceUrl(" "));
        //最后删除
        if(!file.delete()) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        return Result.success(HttpStatus.SC_OK, "ture");
    }

    /*
     *update
     */
    @ApiOperation(value = "修改章节")
    @PostMapping("/udChapter/{cou_id}")
    public Result udChapter(@PathVariable Long cou_id, @RequestBody CourseChapter courseChapter) {
        //先拿到原来章节的时长
        CourseChapter oldCha = courseChapterService.getOne(new QueryWrapper<CourseChapter>().eq("id", courseChapter.getId()));
        //先修改章节信息
        if(!courseChapterService.udChapter(courseChapter)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        //修改课程时长
        Course course = courseService.getOne(new QueryWrapper<Course>().eq("id", cou_id));
        //原总课程时长-原章节时长+新章节时长
        if (courseChapter.getTime() != null) {
            course.setTime(course.getTime()-oldCha.getTime()+courseChapter.getTime());
            if(!courseService.udCourse(course)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "课程时长重算失败");
        }

        return Result.success(HttpStatus.SC_OK, "ture");
    }
    @ApiOperation(value = "修改章节顺序")
    @PostMapping("/udChaIndex")
    public Result udChaIndex(@RequestBody CourseChapterVO1 courseChapters) {

        List<Map<String, Integer>> ids = courseChapters.getIds();

        Iterator<Map<String, Integer>> iterator = ids.iterator();
        while(iterator.hasNext()) {
            Map<String, Integer> map = iterator.next();
            int id = Integer.parseInt(String.valueOf(map.get("id")));
            int chaIndex = Integer.parseInt(String.valueOf(map.get("chaIndex")));
            CourseChapter courseChapter = new CourseChapter().setId(id).setChaIndex(chaIndex);
            //更新课程章节
            courseChapterService.udChapter(courseChapter);
        }

        return Result.success(HttpStatus.SC_OK, "ture");
    }

    /*
     *search
     */

}