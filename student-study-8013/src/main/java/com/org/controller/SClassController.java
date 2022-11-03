package com.org.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.entity.MClass;
import com.org.service.ISClassService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("app/class")
public class SClassController {
    @Autowired
    ISClassService isClassService;
    //查询班级详细信息
    @RequestMapping("shClass/{classid}")
    public ServerResponseVO getClass(HttpServletRequest request, @PathVariable Long classid){
        QueryWrapper<MClass> qw = new QueryWrapper<>();
        qw.eq("id",classid);
        return ServerResponseVO.success(isClassService.getOne(qw));
    }
}
