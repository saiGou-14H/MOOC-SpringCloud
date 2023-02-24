package com.org.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.org.entity.MIntegralHistory;
import com.org.entity.MUser;
import com.org.service.IUserService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseEnum;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("app/user")
public class UserController {
    @Autowired
    IUserService iUserService;

    @RequestMapping("shUser/{userid}")
    public ServerResponseVO shUser(@PathVariable Long userid){
        QueryWrapper<MUser> qw = new QueryWrapper<>();
        qw.eq("id",userid);
        return ServerResponseVO.success(iUserService.getOne(qw));
    }

    @RequestMapping("shUser")
    public ServerResponseVO shUser2(HttpServletRequest request){
        Long StuId = JwtUtil.getId(request);
        QueryWrapper<MUser> qw = new QueryWrapper<>();
        qw.eq("id",StuId);
        return ServerResponseVO.success(iUserService.getOne(qw));
    }

    @RequestMapping("sign")
    public ServerResponseVO sign(HttpServletRequest request){
        Long StuId = JwtUtil.getId(request);
        QueryWrapper<MUser> qw = new QueryWrapper<>();
        qw.eq("id",StuId);
        MUser user = iUserService.getOne(qw);

        if (user.getIssign())return ServerResponseVO.error(ServerResponseEnum.ERROR);

        int index = 0;
        for (int i = 0; i < user.getSign().length(); i++) {
            if (user.getSign().charAt(i)=='0'){
                index = i;
                break;
            }
        }
        int num=1;
        for (int i = 1; i <7-index ; i++) {
            num*=10;
        }
        System.out.println(Integer.valueOf(user.getSign())+num);
        user.setSign(String.valueOf(Integer.valueOf(user.getSign())+num));
        user.setIssign(true);
        UpdateWrapper<MUser> uw = new UpdateWrapper<>();
        uw.eq("id",StuId);
        iUserService.update(user,uw);
        return ServerResponseVO.success(IntegralHistoryController.adIntegralHistory(request,IntegralHistoryController.UP,index+1,true));
    }


}
