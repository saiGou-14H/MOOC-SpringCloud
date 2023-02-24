package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MPractice;
import com.org.mapper.ISPracticeMapper;
import com.org.service.ISPracticeService;
import org.springframework.stereotype.Service;

@Service
public class SPracticeServiceImpl extends ServiceImpl<ISPracticeMapper, MPractice> implements ISPracticeService {
}
