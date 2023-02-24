package com.org.service.impl;

import com.org.model.Course;
import com.org.mapper.CourseMapper;
import com.org.model.dto.QuestionAnswerDTO1;
import com.org.model.vo.Course1;
import com.org.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course1> shCourse(Course1 course1) {
        return courseMapper.shCourse(course1);
    }

    @Override
    public boolean udCourse(Course course) {
        return courseMapper.udCourse(course);
    }

    @Override
    public List<QuestionAnswerDTO1> shQueAns(Map<String, Long> map) {
        return courseMapper.shQueAns(map);
    }

    @Override
    public List<QuestionAnswerDTO1> shQueAns2(Map<String, Long> map) {
        return courseMapper.shQueAns2(map);
    }
}
