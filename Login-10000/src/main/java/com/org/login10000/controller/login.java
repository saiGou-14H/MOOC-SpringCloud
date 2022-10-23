package com.org.login10000.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.org.entity.Aut;
import com.org.login10000.service.AutService;
import com.org.util.ServerResponseEnum;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class login {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    AutService autService;

    //模拟wx第三方认证平台登录
    @RequestMapping("/wxlogin")
    public ServerResponseVO WechatLogin(HttpServletRequest request) {
        String code = request.getParameter("code");
        System.out.println(code);
        String result_str = restTemplate.getForObject("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx7b7f0fc8f32d1a53&secret=12b147fb5ccf8b0bf450defc510189a9&code=" + code + "&grant_type=authorization_code", String.class);
        JSONObject result = JSON.parseObject(result_str);
        if (!result.containsKey("access_token"))
            return ServerResponseVO.error(ServerResponseEnum.ERROR);
        return ServerResponseVO.success(result);
    }

    //模拟第三方认证平台登录
    @RequestMapping("/login")
    public ServerResponseVO Login(@RequestBody String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        String password = (String) jsonObject.get("password");
        System.out.println(jsonObject);
        Aut aut = autService.selectOne((String) jsonObject.get("email"));
        if (aut.getPassword().equals(password)) {
            Map<String, String> map = new HashMap<>();
            System.out.println(aut.getId());
            System.out.println(aut.getAutId());
            System.out.println(aut.getUsername());
            map.put("id", String.valueOf(aut.getAutId()));
            map.put("email", aut.getEmail());
            return ServerResponseVO.success(map);//返回第三方认证平台雪花ID
        }
        return ServerResponseVO.error(ServerResponseEnum.ERROR);
    }
}
