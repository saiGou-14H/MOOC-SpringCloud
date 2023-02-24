package com.org.model.vo;

import io.swagger.annotations.Api;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Api("章节顺序")
public class CourseChapterVO1 {

    private Long courseId;
    private List<Map<String, Integer>> ids;

}
