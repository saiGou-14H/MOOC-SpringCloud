package com.org.service;

import com.org.model.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.org.model.vo.Course1;

import java.util.List;

/**
 * <p>
 * 课程表	 服务类
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
public interface ICourseService extends IService<Course> {

    public List<Course1> shCourse(Course1 course1);

    public boolean udCourse(Course course);
}
