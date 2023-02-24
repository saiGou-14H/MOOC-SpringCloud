package com.org.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.entity.MClass;
import com.org.entity.MStudentClass;
import com.org.mapper.ISStudentClassMapper;
import com.org.service.ISClassService;
import com.org.service.ISStudentClassService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("app/student")
public class SStudentClassController {
    @Autowired
    ISStudentClassService isStudentClassService;

    //查询学生所在班级
    @RequestMapping("shClass")
    public ServerResponseVO getClass(HttpServletRequest request){
        Long stuId = JwtUtil.getId(request);
        QueryWrapper<MStudentClass> qw = new QueryWrapper<>();
        qw.eq("stu_id",stuId);
        return ServerResponseVO.success(isStudentClassService.list(qw));
    }
}
