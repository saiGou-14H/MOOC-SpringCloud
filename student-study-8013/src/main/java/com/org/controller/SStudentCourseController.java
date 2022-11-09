package com.org.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.org.entity.MCourse;
import com.org.entity.MStudentCourse;
import com.org.service.ISCourseService;
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

    @Autowired
    ISCourseService iCourseService;
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
            boolean success = isStudentCourseService.update(getOne,uw);

            QueryWrapper<MCourse> qw1 = new QueryWrapper<>();
            qw1.eq("id",courseid);
            MCourse mCourse = iCourseService.getOne(qw1);
            if(getOne.getRecommend()){
                mCourse.setRecoNum(mCourse.getRecoNum()+1);
            }else{
                mCourse.setRecoNum(mCourse.getRecoNum()-1);
            }
            iCourseService.update(mCourse,qw1);

            return ServerResponseVO.massage(getOne.getRecommend(),"点赞成功","取消点赞成功");
        }
        MStudentCourse mStudentCourse = new MStudentCourse();
        mStudentCourse.setStuId(stuId);
        mStudentCourse.setCouId(courseid);
        mStudentCourse.setRecommend(true);
        QueryWrapper<MCourse> qw1 = new QueryWrapper<>();
        qw1.eq("id",courseid);

        MCourse mCourse = iCourseService.getOne(qw1);
        mCourse.setRecoNum(mCourse.getRecoNum()+1);
        iCourseService.update(mCourse,qw1);

        return ServerResponseVO.massage(isStudentCourseService.save(mStudentCourse),"点赞成功","取消点赞成功");
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

            QueryWrapper<MCourse> qw1 = new QueryWrapper<>();
            qw1.eq("id",courseid);
            MCourse mCourse = iCourseService.getOne(qw1);
            if(getOne.getCollect()){
                mCourse.setCollNum(mCourse.getCollNum()+1);
            }else{
                mCourse.setCollNum(mCourse.getCollNum()-1);
            }
            iCourseService.update(mCourse,qw1);

            boolean success = isStudentCourseService.update(getOne,uw);
            return ServerResponseVO.massage(getOne.getCollect(),"收藏成功","取消收藏成功");
        }

        QueryWrapper<MCourse> qw1 = new QueryWrapper<>();
        qw1.eq("id",courseid);
        MCourse mCourse = iCourseService.getOne(qw1);
        mCourse.setCollNum(mCourse.getCollNum()+1);
        iCourseService.update(mCourse,qw1);

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

    //查看自选课程
    @RequestMapping("shHaveCourse")
    public ServerResponseVO getHaveCourseList(HttpServletRequest request){
        Long stuId = JwtUtil.getId(request);
        return ServerResponseVO.success(isStudentCourseService.getHaveCourseList(stuId));
    }

    //查询学生课程关联表
    @RequestMapping("shStudentCourse/{courseid}")
    public ServerResponseVO getHaveCourseList(HttpServletRequest request,@PathVariable Long courseid){
        Long stuId = JwtUtil.getId(request);
        QueryWrapper<MStudentCourse> qw = new QueryWrapper<>();
        qw.eq("stu_id",stuId).eq("cou_id",courseid);
        return ServerResponseVO.success(isStudentCourseService.getOne(qw));
    }

//    //查询课程评价
//    @RequestMapping("shCourseComment/{courseid}")
//    public ServerResponseVO shCourseComment(HttpServletRequest request,@PathVariable Long courseid){
//        QueryWrapper<MStudentCourse> qw = new QueryWrapper<>();
//        qw.eq("cou_id",courseid).isNotNull("comment");
//        return ServerResponseVO.success(isStudentCourseService.list(qw));
//    }
}
