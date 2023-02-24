package com.org.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.org.model.CourseType;
import com.org.model.Result;
import com.org.service.ICourseTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程类别字典表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@Api("课程类别")
@RestController
@RequestMapping("/courseType")
public class CourseTypeController {

    @Autowired
    private ICourseTypeService courseTypeService;

    /*
    * add
    * */
    @ApiOperation(value = "创建课程")
    @GetMapping("/adCourseType/{id}/{type}/{parent_id}")
    public boolean adCourseType(@PathVariable("id") Long id, @PathVariable("type") String type, @PathVariable("parent_id") Long parentID) {
        CourseType courseType = new CourseType();
        courseType.setId(id).setType(type).setParentId(parentID);
        System.out.println("这是关联"+courseType);
        return courseTypeService.save(courseType);
    }

    /*
     * delete
     * */

    /*
     * update
     * */

    /*
     * search
     * */
    @ApiOperation(value = "查询课程类别")
    @GetMapping("/shCourseType")
    public Result shCourseType() {
        return Result.success(HttpStatus.SC_OK, "null",courseTypeService.shCourseType());
    }

}

