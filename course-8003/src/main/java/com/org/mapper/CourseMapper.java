package com.org.mapper;

import com.org.model.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.model.vo.Course1;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程表	 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    public List<Course1> shCourse(Course1 course1);

    public boolean udCourse(Course course);
}
