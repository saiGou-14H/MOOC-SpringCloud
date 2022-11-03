package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MStudentCourse;
import com.org.entity.MStudentCourseCart;
import com.org.mapper.ISCourseCarMapper;
import com.org.service.ISCourseCarService;
import org.springframework.stereotype.Service;

@Service
public class SCourseCaeServiceImpl extends ServiceImpl<ISCourseCarMapper, MStudentCourseCart> implements ISCourseCarService {
}
