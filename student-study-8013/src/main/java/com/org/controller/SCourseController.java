package com.org.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.entity.MCourse;
import com.org.entity.MStudentCourse;
import com.org.entity.MStudentCourseCart;
import com.org.service.ISCourseService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("app/course")
public class SCourseController {
    @Autowired
    ISCourseService iCourseService;

    //分页查询课程
    @RequestMapping("shCourse/{current}/{size}")
    public ServerResponseVO getCourseBypage(@PathVariable Integer current, @PathVariable Integer size){
        Page<MCourse> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        return ServerResponseVO.success(iCourseService.page(page).getRecords());
    }
    //分页模糊查询课程
    @RequestMapping("shCourseLike/{current}/{size}")
    public ServerResponseVO getCourseBypageLike(@PathVariable Integer current, @PathVariable Integer size, @RequestBody String body){
        JSONObject jsonObject = JSON.parseObject(body);
        String like = (String) jsonObject.get("like");
        Page<MCourse> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        QueryWrapper<MCourse> qw = new QueryWrapper<>();
        qw.like("name",like);
        return ServerResponseVO.success(iCourseService.page(page,qw).getRecords());
    }


}
