package com.org.mapper;

import com.org.model.CourseChapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.awt.*;

/**
 * <p>
 * 章节表	 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-11-05
 */
@Mapper
public interface CourseChapterMapper extends BaseMapper<CourseChapter> {

    public boolean udChapter(CourseChapter courseChapter);

    public boolean insertChapter(CourseChapter courseChapter);

    public boolean udVideo(String str);
}
