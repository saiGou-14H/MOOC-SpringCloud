package com.org.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 章节表

 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_course_chapter")
public class MCourseChapter implements Serializable {

private static final long serialVersionUID = 1L;

                            /**
         * 章节id,自增
         */
                                        @TableId(value = "id", type = IdType.AUTO)
                                            private Integer id;

                        /**
         * 章节排序序号
         */
                    @TableField("cha_index")
                            private Integer chaIndex;

                        /**
         * 章节标题
         */
                    @TableField("title")
                            private String title;

                        /**
         * 章节内容
         */
                    @TableField("content")
                            private String content;

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
         * 章节时长（秒）
         */
                                        @TableField(value = "time", fill = FieldFill.INSERT)
                                    private Long time;

                        /**
         * 乐观锁，默认为0
         */
                    @TableField("version")
                            private Integer version;


        }

