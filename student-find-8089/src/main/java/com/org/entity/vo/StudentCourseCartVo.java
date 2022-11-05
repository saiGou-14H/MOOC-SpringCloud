package com.org.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Jie
 * @Date 2022-11-06
 */
@Data
public class StudentCourseCartVo implements Serializable {
    /**
     * 学生id
     */

    private Long stuId;

    /**
     * 课程id
     */

    private Long couId;

    /**
     * 加入购物车时间
     */
    private String addTime;

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
