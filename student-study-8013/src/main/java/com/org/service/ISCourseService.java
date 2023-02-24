package com.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.ResponseVo.CourseVo;
import com.org.ResponseVo.StudentCourseVo;
import com.org.entity.MCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISCourseService extends IService<MCourse> {
    public List<CourseVo> getCourseByClassId(Long id);

    public List<StudentCourseVo> shCourseComment(@Param("courseid")Long courseid);
    public List<MCourse> shCourseByParentType(@Param("type") String type, @Param("current") int current,@Param("size") int size,@Param("like") String like,@Param("paixv") String paixv);
}
