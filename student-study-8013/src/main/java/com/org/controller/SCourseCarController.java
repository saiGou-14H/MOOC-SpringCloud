package com.org.controller;

import com.org.entity.MStudentCourse;
import com.org.entity.MStudentCourseCart;
import com.org.service.ISCourseCarService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("app/car")
public class SCourseCarController {
    @Autowired
    ISCourseCarService isCourseCarService;
    //将课程加入购物车
    @RequestMapping("adCourse/{courseid}")
    public ServerResponseVO addCourseInCar(HttpServletRequest request,@PathVariable("courseid")Long courseid){
        Long stuId = JwtUtil.getId(request);
        System.out.println(stuId);
        MStudentCourseCart mStudentCourseCart = new MStudentCourseCart();
        mStudentCourseCart.setStuId(stuId);
        mStudentCourseCart.setCouId(courseid);
        mStudentCourseCart.setAddTime(LocalDateTime.now());
        return ServerResponseVO.massage(isCourseCarService.save(mStudentCourseCart),"加入购物车成功","加入购物车失败");
    }
}
