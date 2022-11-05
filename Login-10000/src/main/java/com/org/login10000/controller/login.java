package com.org.login10000.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.entity.Aut;
import com.org.entity.MUser;
import com.org.login10000.service.AutService;
import com.org.login10000.service.IUserService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseEnum;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class login {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    AutService autService;
    @Autowired
    IUserService iUserService;

    //模拟wx第三方认证平台登录
    @RequestMapping("/wxlogin")
    public ServerResponseVO WechatLogin(HttpServletRequest request) {
        String code = request.getParameter("code");
        System.out.println(code);
        String result_str = restTemplate.getForObject("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx7b7f0fc8f32d1a53&secret=12b147fb5ccf8b0bf450defc510189a9&code=" + code + "&grant_type=authorization_code", String.class);
        JSONObject result = JSON.parseObject(result_str);
        if (!result.containsKey("access_token"))
            return ServerResponseVO.error(ServerResponseEnum.ERROR);
        System.out.println(result);
        String openid = String.valueOf(result.get("openid"));
        System.out.println(openid);
        Aut aut = autService.selectOneByOpenid(openid);

        System.out.println(aut.getAutId());
        String id = String.valueOf(aut.getAutId());
        QueryWrapper<MUser> wrapper = new QueryWrapper<>();      //设置查询条件
        wrapper.eq("id", id);
        MUser user = iUserService.getOne(wrapper);
        if(user!=null){
            System.out.println(user);
            String newToken = JwtUtil.createToken(Long.valueOf(id), user.getUsername(), user.getRole());//创建一个token
            //存储用户数据
            Map<String, Object> map = new HashMap<>();
            map.put("staffno", id);
            map.put("staffname", user.getUsername());
            map.put("role", user.getRole());
            map.put("token", newToken);

            //存到redis中
            redisTemplate.opsForValue().set(newToken, user.toString(), Duration.ofSeconds(43200));     //设置存储时间,12小时

            System.out.println("用户"+user.getId()+"-"+user.getUsername()+"已登录");
            System.out.println("token："+newToken);
            return ServerResponseVO.success(map);
        }
        return ServerResponseVO.error(ServerResponseEnum.ERROR);
    }

    //模拟第三方认证平台登录
    @RequestMapping("/login")
    public ServerResponseVO Login(@RequestBody String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        String password = (String) jsonObject.get("password");
        System.out.println(jsonObject);
        Aut aut = autService.selectOne((String) jsonObject.get("email"));
        if (aut.getPassword().equals(password)) {
            System.out.println(aut.getId());
            System.out.println(aut.getAutId());
            System.out.println(aut.getUsername());
            String id = String.valueOf(aut.getAutId());

            QueryWrapper<MUser> wrapper = new QueryWrapper<>();      //设置查询条件
            wrapper.eq("id", id);
            MUser user = iUserService.getOne(wrapper);
            if(user!=null){
                System.out.println(user);
                String newToken = JwtUtil.createToken(Long.valueOf(id), user.getUsername(), user.getRole());//创建一个token
                //存储用户数据
                Map<String, Object> map = new HashMap<>();
                map.put("staffno", id);
                map.put("staffname", user.getUsername());
                map.put("role", user.getRole());
                map.put("token", newToken);

                //存到redis中
                redisTemplate.opsForValue().set(newToken, user.toString(), Duration.ofSeconds(43200));     //设置存储时间,12小时

                System.out.println("用户"+user.getId()+"-"+user.getUsername()+"已登录");
                System.out.println("token："+newToken);
                return ServerResponseVO.success(map);
            }
        }
        return ServerResponseVO.error(ServerResponseEnum.ERROR);
    }

    @RequestMapping("/loginview")
    public String login1(){
        return ("index");
    }
}
