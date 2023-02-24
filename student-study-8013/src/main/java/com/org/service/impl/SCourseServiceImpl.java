package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.ResponseVo.CourseVo;
import com.org.ResponseVo.StudentCourseVo;
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

    @Override
    public List<StudentCourseVo> shCourseComment(Long courseid) {
        return isCourseMapper.shCourseComment(courseid);
    }

    @Override
    public List<MCourse> shCourseByParentType(String type, int current, int size,String like,String paixv) {
        return isCourseMapper.shCourseByParentType(type,current,size,like,paixv);
    }
}
