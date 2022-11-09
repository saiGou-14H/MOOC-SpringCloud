package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.ResponseVo.CourseVo;
import com.org.entity.MCourse;
import com.org.entity.MStudentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ISStudentCourseMapper extends BaseMapper<MStudentCourse> {

    public List<CourseVo> getHaveCourseList(Long id);
}
