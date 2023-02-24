package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MStudentCourseOrder;
import com.org.mapper.ISCourseCarOrderMapper;
import com.org.service.ISCourseCarOrderService;
import org.springframework.stereotype.Service;

@Service
public class SCourseCarOrderServiceImpl extends ServiceImpl<ISCourseCarOrderMapper, MStudentCourseOrder> implements ISCourseCarOrderService {
}
