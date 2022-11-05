package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.ResponseVo.CourseVo;
import com.org.entity.MCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISCourseMapper extends BaseMapper<MCourse> {
    public List<CourseVo> getCourseByClassId(Long id);
}
