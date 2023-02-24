package com.org.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 *
 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_user")
public class MUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级ID，雪花ID后8位
     */
    @TableId("id")
    private Long id;

    /**
     * 真实姓名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 1为学生，2为教师
     * 默认为学生
     */
    @TableField("role")
    private Integer role;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * qq邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 总学习时长（秒）
     */
    @TableField("study_time")
    private Long studyTime;

    /**
     * 学习积分
     */
    @TableField("integral")
    private Integer integral;

    /**
     * 职务
     */
    @TableField("position")
    private String position;

    /**
     * 头像
     */
    @TableField("head_pic")
    private String headPic;

    /**
     * 人脸
     */
    @TableField("face_pic")
    private String facePic;

    /**
     * 个人介绍
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 周打卡，默认为7个0
     */
    @TableField("sign")
    private String sign;

    /**
     * 当天是否签到
     */
    @TableField("issign")
    private Boolean issign;

    /**
     * 当日学习时长（秒）
     */
    @TableField("today_study_time")
    private Long todayStudyTime;


}

