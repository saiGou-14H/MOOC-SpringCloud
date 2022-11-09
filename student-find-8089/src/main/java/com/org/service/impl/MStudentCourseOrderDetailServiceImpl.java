package com.org.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MStudentCourseOrderDetail;
import com.org.mapper.MStudentCourseOrderDetailMapper;
import com.org.service.MStudentCourseOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 订单明细表
 * 服务实现类
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
@Service
public class MStudentCourseOrderDetailServiceImpl extends ServiceImpl<MStudentCourseOrderDetailMapper, MStudentCourseOrderDetail> implements MStudentCourseOrderDetailService {
    @Autowired
    private MStudentCourseOrderDetailMapper mStudentCourseOrderDetailMapper;

}

