package com.org.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 章节表
 *
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_course_chapter")
public class MCourseChapter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 章节id,自增
     */
    @TableId("id")
    private Integer id;

    /**
     * 章节排序序号
     */
    @TableField("index")
    private Integer index;

    /**
     * 章节标题
     */
    @TableField("title")
    private String title;

    /**
     * 课程id
     */
    @TableField("course_id")
    private Long courseId;

    /**
     * 资源地址
     */
    @TableField("resource_url")
    private String resourceUrl;

    /**
     * 章节时长
     */
    @TableField("time")
    private String time;

    /**
     * 乐观锁，默认为0
     */
    @TableField("version")
    private Integer version;


}

