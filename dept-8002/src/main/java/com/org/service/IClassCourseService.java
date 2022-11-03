package com.org.service;

import com.org.model.ClassCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.org.model.Result;

import java.util.List;

/**
 * <p>
 * 班级课程关联表	 服务类
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
public interface IClassCourseService extends IService<ClassCourse> {

    public List<String> shCourses(long cla_id);

    public List<Long> shCoursesPra(long cla_id);
}
