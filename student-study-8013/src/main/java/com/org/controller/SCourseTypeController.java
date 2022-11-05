package com.org.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.entity.MCourseType;
import com.org.service.ISCourseTypeService;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/course/type")
public class SCourseTypeController {
    @Autowired
    ISCourseTypeService isCourseTypeService;

    @RequestMapping("shTypeById/{id}")
    public ServerResponseVO shTypeById(@PathVariable long id){
        QueryWrapper<MCourseType> qw = new QueryWrapper<>();
        qw.eq("id",id);
        return ServerResponseVO.success(isCourseTypeService.getOne(qw));
    }

    @RequestMapping("shChileTypeByPraId/{id}")
    public ServerResponseVO shChileTypeByPraId(@PathVariable long id){
        QueryWrapper<MCourseType> qw = new QueryWrapper<>();
        qw.eq("parent_id",id);
        return ServerResponseVO.success(isCourseTypeService.list(qw));
    }

    @RequestMapping("shMaxType")
    public ServerResponseVO shMaxType(){
        return ServerResponseVO.success(isCourseTypeService.shMaxType());
    }

    @RequestMapping("shTypeByType")
    public ServerResponseVO shTypeByName(@RequestBody String body){
        JSONObject jsonObject = JSON.parseObject(body);
        String type = (String) jsonObject.get("type");
        QueryWrapper<MCourseType> qw = new QueryWrapper<>();
        qw.eq("type",type);
        return ServerResponseVO.success(isCourseTypeService.getOne(qw));
    }
}
