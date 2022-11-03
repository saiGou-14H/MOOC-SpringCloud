package com.org.service.impl;

import com.org.model.StudentClass;
import com.org.mapper.StudentClassMapper;
import com.org.service.IStudentClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 学生班级关联表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@Service
public class StudentClassServiceImpl extends ServiceImpl<StudentClassMapper, StudentClass> implements IStudentClassService {

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Override
    public List<Long> shClassStu(Long cla_id) {
        return studentClassMapper.shClassStu(cla_id);
    }
}
