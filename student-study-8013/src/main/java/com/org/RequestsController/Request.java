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
@RequestMapping("App")
public class Request {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("course/shClass")
    public ResponseEntity<ClassResponse> get(HttpServletRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", request.getHeader("Authorization"));
        //封装请求头
        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<MultiValueMap<String, Object>>(headers);

        ResponseEntity<ClassResponse> classResponse = restTemplate.postForEntity("http://STUDENT-STUDY-8013/app/student/shClass",formEntity,ClassResponse.class);
        for (int i = 0; i < classResponse.getBody().getData().size(); i++) {
            int classId = classResponse.getBody().getData().get(i).getClassId();
            String classEntity= restTemplate.getForObject("http://STUDENT-STUDY-8013/app/class/shClass/"+classId,String.class);
            JSONObject jsonObject = JSON.parseObject(classEntity);
            ClassResponse.ClassListEntity.ClassEntity classEntity1 = new Gson().fromJson(String.valueOf(jsonObject.get("data")),ClassResponse.ClassListEntity.ClassEntity.class);
            classResponse.getBody().getData().get(i).setClassX(classEntity1);
        }
        return classResponse;
    }



    @RequestMapping("course/shTypeByType")
    public ResponseEntity<CourseTypeResponse> shTypeByType(HttpServletRequest request, @RequestBody String body){
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
            courseTypeResponseResponseEntity.getBody().getData().setParent(a.getBody().getData());
        }
        return courseTypeResponseResponseEntity;
    }

    @RequestMapping("course/shTypeById/{id}")
    public ResponseEntity<CourseTypeResponse> shTypeById(HttpServletRequest request, @PathVariable Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", request.getHeader("Authorization"));
        //封装请求头
        HttpEntity<Map<String, Object>> formEntity = new HttpEntity<Map<String, Object>>(headers);

        ResponseEntity<CourseTypeResponse> courseTypeResponseResponseEntity = restTemplate.postForEntity("http://STUDENT-STUDY-8013/app/course/type/shTypeById/"+id,formEntity,CourseTypeResponse.class);
        Integer parentId = (Integer) courseTypeResponseResponseEntity.getBody().getData().getParentId();
        if(parentId!=null){
            ResponseEntity<CourseTypeResponse> a = restTemplate.postForEntity("http://STUDENT-STUDY-8013/run/2/"+parentId,formEntity,CourseTypeResponse.class);
            System.out.println(a.getBody().getData());
            courseTypeResponseResponseEntity.getBody().getData().setParent((a.getBody().getData()));
        }
        return courseTypeResponseResponseEntity;
    }


}
