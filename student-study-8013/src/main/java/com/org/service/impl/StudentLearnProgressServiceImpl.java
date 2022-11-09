package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MLearnProgress;
import com.org.mapper.IStudentLearnProgressMapper;
import com.org.service.IStudentLearnProgressService;
import org.springframework.stereotype.Service;

@Service
public class StudentLearnProgressServiceImpl extends ServiceImpl<IStudentLearnProgressMapper, MLearnProgress> implements IStudentLearnProgressService {
}
