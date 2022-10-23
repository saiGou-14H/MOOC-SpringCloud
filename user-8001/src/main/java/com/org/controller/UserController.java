package com.org.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.config.utils.JwtUtil;
import com.org.model.Result;
import com.org.model.User;
import com.org.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 用户表
 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /*消费者不应该有service层
     * 我们之间调用RestTemplate，注册到spring中
     * (url, 实体:Map, Class<T> responseType)
     * */
    @Autowired
    private RestTemplate restTemplate;      //提供多种便捷访问远程http服务的方法，简单的Restful服务模板
    //Ribbon,这里的地址应该是一个变量，通过服务器名来访问
    //private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX_DEPT = "http://DEPT-8002";
    private static final String REST_URL_PREFIX_COURSE = "http://COURSE-8003";
    private static final String REST_URL_PREFIX_COMMUNITY = "http://COMMUNITY-8004";

    @Autowired
    private IUserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result login(@RequestBody String data) {
        JwtUtil jwtUtil = new JwtUtil();

        JSONObject jsonObject =  JSON.parseObject(data);
        String id = String.valueOf(jsonObject.get("id"));
        String password = String.valueOf(jsonObject.get("password"));

        QueryWrapper<User> wrapper = new QueryWrapper<>();      //设置查询条件
        wrapper.eq("id", id);
        wrapper.eq("password", password);//先转为MD5
        User user = userService.getOne(wrapper);               //获取指定用户

        Map<String, Object> map = new HashMap<>();
        System.out.println(user);
        if(user != null) {                            //查到有此用户

            String newToken = jwtUtil.createToken(id, user.getUsername(), user.getRole());//创建一个token
            //存储用户数据
            map.put("staffno", id);
            map.put("staffname", user.getUsername());
            map.put("role", user.getRole());
            map.put("token", newToken);

            //存到redis中
            redisTemplate.opsForValue().set(newToken, user.toString(), Duration.ofSeconds(43200));     //设置存储时间,12小时

            System.out.println("用户"+user.getId()+"-"+user.getUsername()+"已登录");
            System.out.println("token："+newToken);
        }
        else {return Result.failure(500, "请检查您的账号或密码是否有误");}

        return Result.success(map);                      //返回结果集
    }

//    @RequestMapping("/searchOne/{deptno}")
//    public Dept searchOne(@PathVariable("deptno") int deptno) {
//        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/searchOne/"+deptno, Dept.class);
//    }
//
    @GetMapping("/searchAllUser")
    public List<User> searchAllUser() {
        return userService.list();
    }


}

