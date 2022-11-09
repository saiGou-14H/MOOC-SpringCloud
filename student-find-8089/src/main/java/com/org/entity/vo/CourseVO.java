package com.org.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Jie
 * @Date 2022-11-09
 */
@Data
public class CourseVO implements Serializable {
    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程积分（价格）
     */
    private Integer integral;
    /**
     * 课程封面图片
     */
    private String picture;
}
