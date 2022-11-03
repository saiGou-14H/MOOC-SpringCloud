package com.org.service.impl;

import com.org.model.ClassCourse;
import com.org.mapper.ClassCourseMapper;
import com.org.service.IClassCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 班级课程关联表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@Service
public class ClassCourseServiceImpl extends ServiceImpl<ClassCourseMapper, ClassCourse> implements IClassCourseService {

    @Autowired
    ClassCourseMapper classCourseMapper;

    @Override
    public List<String> shCourses(long cla_id) {
        return classCourseMapper.shCourses(cla_id);
    }

    @Override
    public List<Long> shCoursesPra(long cla_id) {
        return classCourseMapper.shCoursesPra(cla_id);
    }
}
