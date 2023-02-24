package com.org.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 学习进度表

 * </p>
 *
 * @author Jie
 * @since 2022-12-03
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
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;

                        /**
         * 最新开始进度条时间（纳秒）
         */
                    @TableField("next_start_time")
                            private Long nextStartTime;

                        /**
         * 实际已完成观看时长（纳秒）
         */
                    @TableField("read_time")
                            private Long readTime;

                        /**
         * 今日观看时长（纳秒）
         */
                    @TableField("day_time")
                            private Long dayTime;

                        /**
         * 章节学习进度（0-1）
         */
                    @TableField("progress")
                            private Float progress;

                                        @TableId(value = "id", type = IdType.AUTO)
                                            private Integer id;


        }

