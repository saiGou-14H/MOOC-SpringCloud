package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MStudentClass;
import com.org.mapper.ISStudentClassMapper;
import com.org.service.ISStudentClassService;
import org.springframework.stereotype.Service;

@Service
public class SStudentClassServiceImpl extends ServiceImpl<ISStudentClassMapper, MStudentClass> implements ISStudentClassService {
}
