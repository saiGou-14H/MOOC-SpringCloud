package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MStudentPractice;
import com.org.mapper.ISStudentPracticeMapper;
import com.org.service.ISStudentClassService;
import com.org.service.ISStudentPracticeService;
import org.springframework.stereotype.Service;

@Service
public class SStudentPracticeService extends ServiceImpl<ISStudentPracticeMapper, MStudentPractice> implements ISStudentPracticeService {
}
