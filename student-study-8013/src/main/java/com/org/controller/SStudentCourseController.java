package com.org.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.org.entity.MStudentCourse;
import com.org.service.ISStudentCourseService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Wrapper;

@RestController
@RequestMapping("app/student")
public class SStudentCourseController {
    @Autowired
    ISStudentCourseService isStudentCourseService;
    //购买课程
    @RequestMapping("adCourse/{courseid}")
    public ServerResponseVO addCourse(HttpServletRequest request, @PathVariable("courseid")Long courseid){
        Long stuId = JwtUtil.getId(request);
        QueryWrapper<MStudentCourse> qw = new QueryWrapper<>();
        qw.eq("stu_id",stuId);
        qw.eq("cou_id",courseid);
        MStudentCourse getOne = isStudentCourseService.getOne(qw);
        if (getOne!=null){
            getOne.setHave(true);
            UpdateWrapper uw = new UpdateWrapper();
            uw.eq("stu_id",stuId);
            uw.eq("cou_id",courseid);
            return ServerResponseVO.massage(isStudentCourseService.update(getOne,uw),"购买课程成功","购买课程失败");
        }

        MStudentCourse mStudentCourse = new MStudentCourse();
        mStudentCourse.setStuId(stuId);
        mStudentCourse.setCouId(courseid);
        mStudentCourse.setHave(true);
        return ServerResponseVO.massage(isStudentCourseService.save(mStudentCourse),"购买课程成功","购买课程失败");
    }

    //点赞课程
    @RequestMapping("up/{courseid}")
    public ServerResponseVO UpCourse(HttpServletRequest request,@PathVariable("courseid")Long courseid){
        Long stuId = JwtUtil.getId(request);
        QueryWrapper<MStudentCourse> qw = new QueryWrapper<>();
        qw.eq("stu_id",stuId);
        qw.eq("cou_id",courseid);
        MStudentCourse getOne = isStudentCourseService.getOne(qw);
        if (getOne!=null){
            getOne.setRecommend(!getOne.getRecommend());
            UpdateWrapper uw = new UpdateWrapper();
            uw.eq("stu_id",stuId);
            uw.eq("cou_id",courseid);
            return ServerResponseVO.success(isStudentCourseService.update(getOne,uw));
        }

        MStudentCourse mStudentCourse = new MStudentCourse();
        mStudentCourse.setStuId(stuId);
        mStudentCourse.setCouId(courseid);
        mStudentCourse.setRecommend(true);
        return ServerResponseVO.success(isStudentCourseService.save(mStudentCourse));
    }

    //收藏课程
    @RequestMapping("collect/{courseid}")
    public ServerResponseVO collectCourse(HttpServletRequest request,@PathVariable("courseid")Long courseid){
        Long stuId = JwtUtil.getId(request);
        QueryWrapper<MStudentCourse> qw = new QueryWrapper<>();
        qw.eq("stu_id",stuId);
        qw.eq("cou_id",courseid);
        MStudentCourse getOne = isStudentCourseService.getOne(qw);
        if (getOne!=null){
            getOne.setCollect(!getOne.getCollect());
            UpdateWrapper uw = new UpdateWrapper();
            uw.eq("stu_id",stuId);
            uw.eq("cou_id",courseid);
            return ServerResponseVO.success(isStudentCourseService.update(getOne,uw));
        }

        MStudentCourse mStudentCourse = new MStudentCourse();
        mStudentCourse.setStuId(stuId);
        mStudentCourse.setCouId(courseid);
        mStudentCourse.setCollect(true);
        return ServerResponseVO.success(isStudentCourseService.save(mStudentCourse));
    }

    //评论课程
    @RequestMapping("comment/{courseid}")
    public ServerResponseVO commentCourse(HttpServletRequest request, @PathVariable("courseid")Long courseid, @RequestBody String body){
        Long stuId = JwtUtil.getId(request);
        JSONObject jsonObject = JSON.parseObject(body);
        String comment = (String) jsonObject.get("comment");

        QueryWrapper<MStudentCourse> qw = new QueryWrapper<>();
        qw.eq("stu_id",stuId);
        qw.eq("cou_id",courseid);
        MStudentCourse getOne = isStudentCourseService.getOne(qw);
        if (getOne!=null){
            getOne.setComment(comment);
            UpdateWrapper uw = new UpdateWrapper();
            uw.eq("stu_id",stuId);
            uw.eq("cou_id",courseid);
            return ServerResponseVO.success(isStudentCourseService.update(getOne,uw));
        }

        MStudentCourse mStudentCourse = new MStudentCourse();
        mStudentCourse.setStuId(stuId);
        mStudentCourse.setCouId(courseid);
        mStudentCourse.setComment(comment);
        return ServerResponseVO.success(isStudentCourseService.save(mStudentCourse));
    }
}
