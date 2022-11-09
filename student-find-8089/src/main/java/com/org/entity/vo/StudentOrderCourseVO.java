package com.org.entity.vo;

import lombok.Data;

/**
 * @Author Jie
 * @Date 2022-11-09
 */
@Data
public class StudentOrderCourseVO {

    /**
     * 订单创建时间
     */
    private String createTime;
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
