package com.org.service.impl;

import com.org.model.Class;
import com.org.mapper.ClassMapper;
import com.org.service.IClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 班级表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {

    @Autowired
    ClassMapper classMapper;

    @Override
    public boolean updateCourse(Class classData) {
        return classMapper.updateCourse(classData);
    }

    @Override
    public List<Class> shClass(Long class_id) {
        return classMapper.shClass(class_id);
    }
}
