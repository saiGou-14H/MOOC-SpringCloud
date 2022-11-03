package com.org.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 学习进度表
 *
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_learn_progress")
public class MLearnProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    @TableField("stu_id")
    private Long stuId;

    /**
     * 课程id
     */
    @TableField("cou_id")
    private Long couId;

    /**
     * 班级id
     */
    @TableField("class_id")
    private Long classId;

    /**
     * 章节id
     */
    @TableField("cha_id")
    private Integer chaId;

    /**
     * 章节在课程中的序号
     */
    @TableField("cha_index")
    private Integer chaIndex;

    /**
     * 记录更新的时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private String updateTime;

    /**
     * 最新开始进度条时间
     */
    @TableField("start_time")
    private String startTime;

    /**
     * 最新章节看到的时间
     */
    @TableField("end_time")
    private String endTime;

    /**
     * 实际已完成观看时长
     */
    @TableField("read_time")
    private String readTime;

    /**
     * 今日观看时长
     */
    @TableField("day_time")
    private String dayTime;


}

