package com.org.service.impl;

import com.org.model.CourseType;
import com.org.mapper.CourseTypeMapper;
import com.org.service.ICourseTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程类别字典表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    CourseTypeMapper courseTypeMapper;

    @Override
    public List<CourseType> shCourseType() {
        return courseTypeMapper.shCourseType();
    }
}
