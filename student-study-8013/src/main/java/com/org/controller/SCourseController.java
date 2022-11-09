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
    public ServerResponseVO getCourseBypage(@PathVariable Integer current, @PathVariable Integer size,@RequestBody String body){
        Page<MCourse> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        JSONObject jsonObject = JSON.parseObject(body);
        QueryWrapper<MCourse> qw = new QueryWrapper<>();
        if(jsonObject.get("like")!=null){
            String like = (String) jsonObject.get("like");
            qw.like("name",like);
        }
        if(jsonObject.get("paixv")!=null&&jsonObject.get("paixv")!=""&&!jsonObject.get("paixv").equals("default")){
            String paixv = (String) jsonObject.get("paixv");
            System.out.println(paixv);
            qw.orderByDesc(paixv);
        }
        return ServerResponseVO.success(iCourseService.page(page,qw).getRecords());
    }


    //根据父类别分页查询课程/模糊查询
    @RequestMapping("shCourseByParentType/{type}/{current}/{size}")
    public ServerResponseVO shCourseByParentType(@PathVariable String type,@PathVariable Integer current, @PathVariable Integer size,@RequestBody String body){
        JSONObject jsonObject = JSON.parseObject(body);
        String like = "";
        String paixv = null;
        if(jsonObject.get("like")!=null){
           like = (String) jsonObject.get("like");
        }
        if(jsonObject.get("paixv")!=null&&jsonObject.get("paixv")!=""&&!jsonObject.get("paixv").equals("default")){
            paixv = (String) jsonObject.get("paixv");
        }
        return ServerResponseVO.success(iCourseService.shCourseByParentType(type,(current-1)*size,size,like,paixv));
    }

    //根据类别分页查询课程
    @RequestMapping("shCourseByType/{type}/{current}/{size}")
    public ServerResponseVO shCourseByType(@PathVariable String type,@PathVariable Integer current, @PathVariable Integer size,@RequestBody String body){
        Page<MCourse> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        QueryWrapper<MCourse> qw = new QueryWrapper<>();
        qw.eq("cou_type",type);
        JSONObject jsonObject = JSON.parseObject(body);
        if(jsonObject.get("like")!=null){
            String like = (String) jsonObject.get("like");
            qw.like("name",like);
        }
        if(jsonObject.get("paixv")!=null&&jsonObject.get("paixv")!=""&&!jsonObject.get("paixv").equals("default")){
            String paixv = (String) jsonObject.get("paixv");
            qw.orderByDesc(paixv);
        }
        return ServerResponseVO.success(iCourseService.page(page,qw).getRecords());
    }



    //根据班级查询课程
    @RequestMapping("shCourse/{classid}")
    public ServerResponseVO getCourseByClassId(@PathVariable Long classid){
        return ServerResponseVO.success(iCourseService.getCourseByClassId(classid));
    }

    //查询课程评论
    @RequestMapping("shCourseComment/{courseid}")
    public ServerResponseVO shCourseComment(@PathVariable Long courseid){
        return ServerResponseVO.success(iCourseService.shCourseComment(courseid));
    }
}
