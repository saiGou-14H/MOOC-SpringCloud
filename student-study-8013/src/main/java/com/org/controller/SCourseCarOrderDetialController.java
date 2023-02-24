package com.org.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.entity.MStudentCourseOrder;
import com.org.entity.MStudentCourseOrderDetail;
import com.org.service.ISCourseCarOrderDetialService;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/car/order/detial")
public class SCourseCarOrderDetialController {
    @Autowired
    ISCourseCarOrderDetialService isCourseCarOrderDetialService;

    //根据订单ID查询订单详情
    @RequestMapping("shDetial/{orderid}")
    public ServerResponseVO shDetial(@PathVariable Long orderid){
        QueryWrapper<MStudentCourseOrderDetail> qw = new QueryWrapper<>();
        qw.eq("order_id",orderid);
        return ServerResponseVO.success(isCourseCarOrderDetialService.list(qw));
    }
}
