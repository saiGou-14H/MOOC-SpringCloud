package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MStudentCourseOrderDetail;
import com.org.mapper.ISCourseCarOrderDetialMapper;
import com.org.service.ISCourseCarOrderDetialService;
import org.springframework.stereotype.Service;

@Service
public class SCourseCarOrderDetialServiceImpl extends ServiceImpl<ISCourseCarOrderDetialMapper, MStudentCourseOrderDetail> implements ISCourseCarOrderDetialService {
}
