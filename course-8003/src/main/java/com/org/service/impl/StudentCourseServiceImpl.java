package com.org.service.impl;

import com.org.model.StudentCourse;
import com.org.mapper.StudentCourseMapper;
import com.org.service.IStudentCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生课程关联表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-10-31
 */
@Service
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourseMapper, StudentCourse> implements IStudentCourseService {

}
