package com.org.service.impl;

import com.org.model.StudentPractice;
import com.org.mapper.StudentPracticeMapper;
import com.org.service.IStudentPracticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生实践关联表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-10-28
 */
@Service
public class StudentPracticeServiceImpl extends ServiceImpl<StudentPracticeMapper, StudentPractice> implements IStudentPracticeService {

}
