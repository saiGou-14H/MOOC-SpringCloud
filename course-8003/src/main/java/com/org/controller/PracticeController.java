package com.org.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.org.model.Practice;
import com.org.model.Result;
import com.org.model.StudentPractice;
import com.org.model.dto.PracticeDTO1;
import com.org.model.vo.Practice1;
import com.org.service.IPracticeService;
import com.org.service.IStudentPracticeService;
import com.org.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 线下实践表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-10-28
 */
@RestController
@RequestMapping("/practice")
public class PracticeController {

    @Autowired
    private RestTemplate restTemplate;      //提供多种便捷访问远程http服务的方法，简单的Restful服务模板
    @Autowired
    private IPracticeService practiceService;
    @Autowired
    private IStudentPracticeService studentPracticeService;

    private static final String REST_URL_PREFIX_STUPRA = "http://COURSE-8003";

    /*
    * add
    * */
    @ApiOperation(value = "创建实践")
    @PostMapping("/ctPractice")
    //@HystrixCommand(fallbackMethod = "hystrixCtPractice")
    public Result ctPractice(@RequestBody Practice practice, @RequestParam Long classId, HttpServletRequest request) {
        try {
            IdGeneratorOptions options = new IdGeneratorOptions((short)1);
            YitIdHelper.setIdGenerator(options);
            Long newId = YitIdHelper.nextId();
            practice.setId(newId/1000000);
            Long tea_id = JwtUtil.getId(request);
            practice.setTeaId(tea_id);
        } catch (Exception e) {e.printStackTrace();}
        //查找是否创建过同名的实践
        if(practiceService.getOne(new QueryWrapper<Practice>().eq("name",practice.getName()).eq("tea_id", practice.getTeaId())) != null) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "您已创建过该实践");
        //先插入数据
        if(!practiceService.save(practice)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");
        //先插入学生与实践关联
        /*Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("pra_id", practice.getId());
        requestMap.put("cou_id", practice.getCouId());
        requestMap.put("cla_id", classId);
        if(!restTemplate.getForObject(REST_URL_PREFIX_STUPRA+"/studentPractice/adStuPractice/{pra_id}/{cla_id}/{cou_id}", boolean.class, requestMap)) throw new RuntimeException("插入课程字典发生错误");
        */
        return Result.success(HttpStatus.SC_OK, "True");
    }
    public Result hystrixCtPractice(@RequestBody Practice practice, @RequestParam Long classId, HttpServletRequest request) {
        return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据或请求头");
    }

    /*
     *delete
     * */
    @ApiOperation(value = "删除实践")
    @GetMapping("/delPractice/{pra_id}")
    public Result delPractice(@PathVariable Long pra_id) {
        //先删除学生实践关联
        studentPracticeService.remove(new QueryWrapper<StudentPractice>().eq("pra_id", pra_id));
        //再删除实践
        practiceService.remove(new QueryWrapper<Practice>().eq("id", pra_id));
        return Result.success(HttpStatus.SC_OK, "True");
    }

    /*
     *update
     * */
    @ApiOperation(value = "修改实践")
    @PostMapping("/udPractice")
    public Result udPractice(@RequestBody Practice practice) {
        if(!practiceService.udPractice(practice)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "False");
        return Result.success(HttpStatus.SC_OK, "True");
    }

    /*
     *search
     * */
    @ApiOperation(value = "查看实践1-模糊查询")
    @GetMapping("/shPractice1")
    public Result shPractice1(@RequestBody Practice1 practice1, HttpServletRequest request) {
        practice1.setTea_id(JwtUtil.getId(request));
        List<PracticeDTO1> practiceList = practiceService.shPractice1(practice1);
        return Result.success(HttpStatus.SC_OK, "null", practiceList);
    }

    @ApiOperation(value = "查看实践2-使用于课程里查看")
    @GetMapping("/shPractice2/{cou_id}")
    public Result shPractice2(@PathVariable("cou_id") Long cou_id, HttpServletRequest request) {

        HashMap<String, Long> params = new HashMap<>();
        params.put("cou_id", cou_id);
        params.put("tea_id", JwtUtil.getId(request));

        List<Practice> practiceList = practiceService.shPractice2(params);
        return Result.success(HttpStatus.SC_OK, "null", practiceList);
    }

    @ApiOperation(value = "查看实践3-适用与实践管理")
    @GetMapping("/shPractice3")
    public Result shPractice2(HttpServletRequest request) {
        List<Practice> practiceList = practiceService.shPractice3(JwtUtil.getId(request));
        return Result.success(HttpStatus.SC_OK, "null", practiceList);
    }

    @ApiOperation(value = "查询课程的所有实践id")
    @GetMapping("/shPracticeIds/cou_id")
    public Result shPracticeIds(@PathVariable Long cou_id) {
        List<String> practiceList = practiceService.shPracticeIds(cou_id);
        return Result.success(HttpStatus.SC_OK, "null", practiceList);
    }

}

