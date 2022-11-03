package com.org.mapper;

import com.org.model.ClassCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 班级课程关联表	 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@Mapper
public interface ClassCourseMapper extends BaseMapper<ClassCourse> {

    public List<String> shCourses(long cla_id);

    public List<Long> shCoursesPra(long cla_id);
}
