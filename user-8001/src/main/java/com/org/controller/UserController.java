package com.org.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.org.config.utils.ImageUtil;
import com.org.model.Course;
import com.org.model.Result;
import com.org.model.User;
import com.org.model.dto.UserDTO1;
import com.org.model.dto.UserDTO2;
import com.org.service.IUserService;
import com.org.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
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
@Api(value = "用户类")
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

    private static final String REST_URL_PREFIX_DEPT = "http://DEPT-8002";
    private static final String REST_URL_PREFIX_COURSE = "http://COURSE-8003";
    private static final String REST_URL_PREFIX_COMMUNITY = "http://COMMUNITY-8004";

    @Autowired
    private IUserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody String data) {
        JwtUtil jwtUtil = new JwtUtil();

        JSONObject jsonObject =  JSON.parseObject(data);
        Long id = Long.parseLong(String.valueOf(jsonObject.get("id")));
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


    /*
    * add
    * */
    @ApiOperation(value = "上传图片")
    @PostMapping("/udPicture")
    public Result udMyself(@RequestParam("file") MultipartFile file) {
        String url = null;

        try {
            url = ImageUtil.multipartFileToFile(file);
        }catch (Exception e) {Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");}

        return Result.success(HttpStatus.SC_OK, "null", "/file/"+url);
    }

    /*
     * delete
     * */

    /*
     * update
     * */
    @ApiOperation(value = "修改个人信息")
    @PostMapping("/udMyself")
    public Result udMyself(@RequestBody User user) {
        //保存头像
        String head_str = ImageUtil.Base64ToPic(user);
        user.setHeadPic("/head"+head_str);
        if(!userService.udMyself(user)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据是否有误");
        return Result.success(HttpStatus.SC_OK, "true");
    }

    /*
     * search
     * */
    @ApiOperation(value = "查询个人信息")
    @GetMapping("/shMyself")
    public Result searchOne(HttpServletRequest request) {

        User user = userService.getOne(new QueryWrapper<User>().eq("id", JwtUtil.getId(request)));
        if(user == null) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查请求头");
        return Result.success(HttpStatus.SC_OK, "null", user);
    }
    @ApiOperation(value = "查询本班级所有学生信息")
    @PostMapping("/shUserByCla")
    public Result shUserByCla(@RequestBody List<Long> stuIds) {
        List<User> userList = userService.listByIds(stuIds);
        return Result.success(HttpStatus.SC_OK, "null", userList);
    }
    @ApiOperation(value = "查询本班级所有学生信息2")
    @GetMapping("/shUserByCla2/{cla_id}")
    public Result shUserByCla2(@PathVariable Long cla_id) {
        List<UserDTO1> userList = userService.shUserByCla2(cla_id);
        return Result.success(HttpStatus.SC_OK, "null", userList);
    }
    @ApiOperation(value =  "查询学生全部课程的学习进度")
    @GetMapping("/shStuLearnedPro/{cla_id}/{stu_id}")
    public Result shStuLearnedPro(@PathVariable Long cla_id, @PathVariable Long stu_id) {
        Map<String, Long> map = new HashMap<>();
        map.put("cla_id", cla_id);
        map.put("stu_id", stu_id);
        List<UserDTO2> userDTO2s = userService.shStuLearnedPro(map);
        return Result.success(HttpStatus.SC_OK, "null", userDTO2s);
    }

    @ApiOperation(value = "模糊查找学生")
    @PostMapping("/shUser")
    public Result shUser(@RequestBody User user) {
        List<User> userList = userService.shUser(user);
        return Result.success(HttpStatus.SC_OK, null, userList);
    }

    @PostMapping("/searchAll")
    public Course searchAll() {
        Map<String, Object> requestmap = new HashMap<>();
        requestmap.put("id", 123);
        requestmap.put("name", "java");
        ResponseEntity<Course> responseEntity = restTemplate.postForEntity(REST_URL_PREFIX_COURSE+"/course/searchAll", requestmap, Course.class);
        System.out.println(responseEntity);
        return responseEntity.getBody();
    }

    @GetMapping("/searchAllUser")
    public List<User> searchAllUser() {
        return userService.list();
    }

    @Test
    public void fun() {
        // 创建 IdGeneratorOptions 对象，请在构造函数中输入 WorkerId：
        IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
// options.WorkerIdBitLength = 10; // WorkerIdBitLength 默认值6，支持的 WorkerId 最大值为2^6-1，若 WorkerId 超过64，可设置更大的 WorkerIdBitLength
// ...... 其它参数设置参考 IdGeneratorOptions 定义，一般来说，只要再设置 WorkerIdBitLength （决定 WorkerId 的最大值）。
// 保存参数（必须的操作，否则以上设置都不能生效）：
        YitIdHelper.setIdGenerator(options);
// 以上初始化过程只需全局一次，且必须在第2步之前设置。
        // 初始化以后，即可在任何需要生成ID的地方，调用以下方法：
        long newId = YitIdHelper.nextId();
        System.out.println(newId);
    }



}

