package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MCourseType;
import com.org.mapper.ISCourseTypeMapper;
import com.org.service.ISCourseTypeService;
import com.org.util.ServerResponseVO;
import org.springframework.stereotype.Service;

@Service
public class SCourseTypeServiceImpl extends ServiceImpl<ISCourseTypeMapper, MCourseType> implements ISCourseTypeService {
}
