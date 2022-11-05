package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.ResponseVo.CourseVo;
import com.org.entity.MCourse;
import com.org.mapper.ISCourseMapper;
import com.org.service.ISCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SCourseServiceImpl extends ServiceImpl<ISCourseMapper, MCourse> implements ISCourseService {
    @Autowired
    ISCourseMapper isCourseMapper;

    @Override
    public List<CourseVo> getCourseByClassId(Long id) {
        return isCourseMapper.getCourseByClassId(id);
    }
}
