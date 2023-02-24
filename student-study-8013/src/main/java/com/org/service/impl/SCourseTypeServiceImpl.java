package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MCourseType;
import com.org.mapper.ISCourseTypeMapper;
import com.org.service.ISCourseTypeService;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SCourseTypeServiceImpl extends ServiceImpl<ISCourseTypeMapper, MCourseType> implements ISCourseTypeService {
    @Autowired
    ISCourseTypeMapper isCourseTypeMapper;
    @Override
    public List<MCourseType> shMaxType() {
        return isCourseTypeMapper.shMaxType();
    }
}
