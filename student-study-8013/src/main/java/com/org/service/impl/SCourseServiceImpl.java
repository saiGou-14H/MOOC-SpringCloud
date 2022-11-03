package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MCourse;
import com.org.mapper.ISCourseMapper;
import com.org.service.ISCourseService;
import org.springframework.stereotype.Service;

@Service
public class SCourseServiceImpl extends ServiceImpl<ISCourseMapper, MCourse> implements ISCourseService {
}
