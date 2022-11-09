package com.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.ResponseVo.CourseVo;
import com.org.entity.MCourse;
import com.org.entity.MStudentCourse;

import java.util.List;

public interface ISStudentCourseService extends IService<MStudentCourse> {
    public List<CourseVo> getHaveCourseList(Long id);
}
