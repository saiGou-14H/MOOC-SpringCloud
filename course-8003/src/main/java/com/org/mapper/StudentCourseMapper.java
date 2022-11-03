package com.org.mapper;

import com.org.model.StudentCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学生课程关联表	 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-10-31
 */
@Mapper
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

}
