package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MCourseChapter;
import com.org.mapper.ISCourseChapterMapper;
import com.org.service.ISCourseChapterService;
import org.springframework.stereotype.Service;

@Service
public class SCourseChapterServiceImpl extends ServiceImpl<ISCourseChapterMapper, MCourseChapter> implements ISCourseChapterService {
}
