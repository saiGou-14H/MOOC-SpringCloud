package com.org.mapper;

import com.org.model.CourseType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程类别字典表	 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@Mapper
public interface CourseTypeMapper extends BaseMapper<CourseType> {

    public List<CourseType> shCourseType();
}
