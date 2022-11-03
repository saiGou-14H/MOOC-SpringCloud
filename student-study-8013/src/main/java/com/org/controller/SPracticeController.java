package com.org.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.entity.MPractice;
import com.org.service.ISPracticeService;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/practice")
public class SPracticeController {
    @Autowired
    ISPracticeService isPracticeService;

    //根据实践ID查询实践信息
    @RequestMapping("shPracticeById/{id}")
    public ServerResponseVO shPracticeById(@PathVariable Long id){
        QueryWrapper<MPractice> qw = new QueryWrapper<>();
        qw.eq("id",id);
        return ServerResponseVO.success(isPracticeService.getOne(qw));
    }

    //工具课程ID查询课程的实践信息
    @RequestMapping("shPracticeByCouserId/{couid}")
    public ServerResponseVO shPracticeByCouserId(@PathVariable Long couid){
        QueryWrapper<MPractice> qw = new QueryWrapper<>();
        qw.eq("cou_id",couid);
        return ServerResponseVO.success(isPracticeService.list(qw));
    }
}
