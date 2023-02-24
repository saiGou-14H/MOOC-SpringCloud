package com.org.service;

import com.org.model.CourseChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.awt.*;

/**
 * <p>
 * 章节表	 服务类
 * </p>
 *
 * @author B.M
 * @since 2022-11-05
 */
public interface ICourseChapterService extends IService<CourseChapter> {

    public boolean udChapter(CourseChapter courseChapter);

    public boolean insertChapter(CourseChapter courseChapter);

}
