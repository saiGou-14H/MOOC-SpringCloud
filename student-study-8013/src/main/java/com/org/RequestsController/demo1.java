package com.org.RequestsController;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.Gson;
import com.org.ResponseVo.ClassResponse;
import com.org.ResponseVo.CourseTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("run")
public class demo1 {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("1")
    public ResponseEntity<CourseTypeResponse> get(HttpServletRequest request, @RequestBody String body){
        JSONObject jsonObject = JSON.parseObject(body);
        String type = (String) jsonObject.get("type");
        HttpHeaders headers = new HttpHeaders();
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("type",type);
        headers.add("Authorization", request.getHeader("Authorization"));
        //封装请求头
        HttpEntity<Map<String, Object>> formEntity = new HttpEntity<Map<String, Object>>(requestBody,headers);

        ResponseEntity<CourseTypeResponse> courseTypeResponseResponseEntity = restTemplate.postForEntity("http://STUDENT-STUDY-8013/app/course/type/shTypeByType",formEntity,CourseTypeResponse.class);
        Integer parentId = (Integer) courseTypeResponseResponseEntity.getBody().getData().getParentId();
        if(parentId!=null){
            ResponseEntity<CourseTypeResponse> a = restTemplate.postForEntity("http://STUDENT-STUDY-8013/run/2/"+parentId,formEntity,CourseTypeResponse.class);
            courseTypeResponseResponseEntity.getBody().setData(a.getBody().getData());
        }
        return courseTypeResponseResponseEntity;
    }

}
