package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MClass;
import com.org.mapper.ISClassMapper;
import com.org.service.ISClassService;
import org.springframework.stereotype.Service;

@Service
public class SClassServiceImpl extends ServiceImpl<ISClassMapper, MClass> implements ISClassService {
}
