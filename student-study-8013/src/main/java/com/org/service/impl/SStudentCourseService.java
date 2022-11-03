package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MStudentCourse;
import com.org.mapper.ISStudentCourseMapper;
import com.org.service.ISStudentCourseService;
import org.springframework.stereotype.Service;

@Service
public class SStudentCourseService extends ServiceImpl<ISStudentCourseMapper, MStudentCourse> implements ISStudentCourseService {
}
