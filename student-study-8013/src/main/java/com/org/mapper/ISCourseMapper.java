package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.ResponseVo.CourseVo;
import com.org.ResponseVo.StudentCourseVo;
import com.org.entity.MCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ISCourseMapper extends BaseMapper<MCourse> {
    public List<CourseVo> getCourseByClassId(Long id);
    public List<StudentCourseVo> shCourseComment(@Param("courseid")Long courseid);
    public List<MCourse> shCourseByParentType(@Param("type") String type, @Param("current") int current, @Param("size") int size,@Param("like")String like,@Param("paixv") String paixv);
}
