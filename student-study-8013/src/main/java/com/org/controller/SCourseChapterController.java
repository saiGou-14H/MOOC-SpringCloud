package com.org.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.entity.MCourseChapter;
import com.org.service.ISCourseChapterService;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("app/chapter")
public class SCourseChapterController {
    @Autowired
    ISCourseChapterService isCourseChapterService;

    @RequestMapping("shChapterByClassId/{courseid}")
    public ServerResponseVO shChapterByClassId(@PathVariable Long courseid){
        QueryWrapper<MCourseChapter> qw = new QueryWrapper<>();
        qw.eq("course_id",courseid);
        List<MCourseChapter> mCourseChapters= isCourseChapterService.list(qw);
        mCourseChapters.sort(Comparator.comparing(MCourseChapter::getChaIndex));
        return ServerResponseVO.success(mCourseChapters);


    }

}
