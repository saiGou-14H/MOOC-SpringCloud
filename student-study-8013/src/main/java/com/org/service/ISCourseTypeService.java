package com.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.entity.MCourseType;

import java.util.List;

public interface ISCourseTypeService extends IService<MCourseType> {
    public List<MCourseType> shMaxType();
}
