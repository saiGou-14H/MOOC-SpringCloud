package com.org.service;

import com.org.model.CourseType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程类别字典表	 服务类
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
public interface ICourseTypeService extends IService<CourseType> {

    public List<CourseType> shCourseType();
}
