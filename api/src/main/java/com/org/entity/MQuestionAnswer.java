package com.org.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 话题/问题表

 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_question_answer")
public class MQuestionAnswer implements Serializable {

private static final long serialVersionUID = 1L;

                            /**
         * 雪花ID后八位
         */
                                        @TableId("id")
                                            private Long id;

                        /**
         * 问题标题
         */
                    @TableField("title")
                            private String title;

                        /**
         * 发布问题/回复的作者id
         */
                    @TableField("au_id")
                            private Long auId;

                        /**
         * 内容
         */
                    @TableField("content")
                            private String content;

                        /**
         * 课程ID
         */
                    @TableField("cou_id")
                            private Long couId;

                        /**
         * 班级ID
         */
                    @TableField("cla_id")
                            private Long claId;

                        /**
         * 资源地址
         */
                    @TableField("resource_url")
                            private String resourceUrl;

                        /**
         * 1被采纳,0否
         */
                    @TableField("is_select")
                            private Boolean isSelect;

                        /**
         * 创建时间
         */
                                        @TableField(value = "create_time", fill = FieldFill.INSERT)
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

                        /**
         * 点赞数
         */
                    @TableField("like_num")
                            private Integer likeNum;

                        /**
         * 评论数
         */
                    @TableField("comment_num")
                            private Integer commentNum;

                        /**
         * 是否审核,0未审核，1已审核，-1审核不通过
         */
                    @TableField("is_check")
                            private Boolean isCheck;

                        /**
         * 逻辑删除
         */
                    @TableField("deleted")
                            private Boolean deleted;


        }

