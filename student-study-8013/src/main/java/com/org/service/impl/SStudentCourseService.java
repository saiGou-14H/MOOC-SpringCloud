package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MCourse;
import com.org.entity.MStudentCourse;
import com.org.mapper.ISStudentCourseMapper;
import com.org.service.ISStudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SStudentCourseService extends ServiceImpl<ISStudentCourseMapper, MStudentCourse> implements ISStudentCourseService {
    @Autowired
    ISStudentCourseMapper isStudentCourseMapper;

    @Override
    public List<MCourse> getHaveCourseList(Long id) {
        return isStudentCourseMapper.getHaveCourseList(id);
    }
}
