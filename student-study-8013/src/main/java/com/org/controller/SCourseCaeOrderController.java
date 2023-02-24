package com.org.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.entity.MStudentCourseOrder;
import com.org.service.ISCourseCarOrderService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("app/car/order")
public class SCourseCaeOrderController {
    @Autowired
    ISCourseCarOrderService isCourseCarOrderService;

    //查询学生的全部订单
    @RequestMapping("shOrder")
    public ServerResponseVO shOrder(HttpServletRequest request){
        Long StuId = JwtUtil.getId(request);
        QueryWrapper<MStudentCourseOrder> qw = new QueryWrapper<>();
        qw.eq("stu_id",StuId);
        return ServerResponseVO.success(isCourseCarOrderService.list(qw));
    }

}
