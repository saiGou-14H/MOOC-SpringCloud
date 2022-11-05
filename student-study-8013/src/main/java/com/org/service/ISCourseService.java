package com.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.ResponseVo.CourseVo;
import com.org.entity.MCourse;

import java.util.List;

public interface ISCourseService extends IService<MCourse> {
    public List<CourseVo> getCourseByClassId(Long id);
}
