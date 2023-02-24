package com.org.service.impl;

import com.org.model.CourseChapter;
import com.org.mapper.CourseChapterMapper;
import com.org.service.ICourseChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

/**
 * <p>
 * 章节表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-11-05
 */
@Service
public class CourseChapterServiceImpl extends ServiceImpl<CourseChapterMapper, CourseChapter> implements ICourseChapterService {

    @Autowired
    CourseChapterMapper courseChapterMapper;

    @Override
    public boolean udChapter(CourseChapter courseChapter) {
        return courseChapterMapper.udChapter(courseChapter);
    }

    @Override
    public boolean insertChapter(CourseChapter courseChapter) {
        return courseChapterMapper.insertChapter(courseChapter);
    }

}
