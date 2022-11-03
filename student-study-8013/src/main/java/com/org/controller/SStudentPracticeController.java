package com.org.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.entity.MStudentPractice;
import com.org.service.ISStudentPracticeService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseEnum;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("app/student")
public class SStudentPracticeController {
    @Autowired
    ISStudentPracticeService isStudentPracticeService;

    //查询学生全部实践
    @RequestMapping("shPractice")
    public ServerResponseVO shPractice(HttpServletRequest request){
        Long StuId = JwtUtil.getId(request);
        QueryWrapper<MStudentPractice> qw =new QueryWrapper<>();
        qw.eq("stu_id",StuId);
        return ServerResponseVO.success(isStudentPracticeService.getOne(qw));
    }

    //学生报名实践
    @RequestMapping("adPractice/{praid}")
    public ServerResponseVO adPractice(HttpServletRequest request, @PathVariable Long praid){
        Long StuId = JwtUtil.getId(request);
        QueryWrapper<MStudentPractice> qw = new QueryWrapper<>();
        qw.eq("stu_id",StuId).eq("pra_id",praid);
        if(isStudentPracticeService.getOne(qw)!=null){
            return ServerResponseVO.massage(false,"","添加实践失败，已报名该实践");
        }
        MStudentPractice mStudentPractice = new MStudentPractice();
        mStudentPractice.setStuId(StuId);
        mStudentPractice.setPraId(praid);
        mStudentPractice.setPraDate(LocalDateTime.now());
        return ServerResponseVO.massage(isStudentPracticeService.save(mStudentPractice),"报名实践成功","报名实践失败");
    }

    //学生取消实践
    @RequestMapping("delPractice/{praid}")
    public ServerResponseVO delPractice(HttpServletRequest request, @PathVariable Long praid){
        Long StuId = JwtUtil.getId(request);
        QueryWrapper<MStudentPractice> qw = new QueryWrapper<>();
        qw.eq("stu_id",StuId).eq("pra_id",praid);
        return ServerResponseVO.massage(isStudentPracticeService.remove(qw),"取消实践成功","取消实践失败");
    }
}
