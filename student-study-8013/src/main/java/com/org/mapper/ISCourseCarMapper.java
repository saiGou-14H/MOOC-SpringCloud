package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.MStudentCourse;
import com.org.entity.MStudentCourseCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISCourseCarMapper extends BaseMapper<MStudentCourseCart> {
}
