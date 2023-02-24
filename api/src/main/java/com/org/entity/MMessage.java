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
 * 资讯表

 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_message")
public class MMessage implements Serializable {

private static final long serialVersionUID = 1L;

                            /**
         * 资讯id（雪花id）
         */
                                        @TableId("id")
                                            private Long id;

                        /**
         * 资讯标题
         */
                    @TableField("title")
                            private String title;

                        /**
         * 资讯内容
         */
                    @TableField("content")
                            private String content;

                        /**
         * 资源地址
         */
                    @TableField("resource_url")
                            private String resourceUrl;

                        /**
         * 资讯作者
         */
                    @TableField("author")
                            private String author;

                        /**
         * 发布日期
         */
                                        @TableField(value = "date", fill = FieldFill.INSERT)
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime date;

                        /**
         * 点赞数
         */
                    @TableField("message_like")
                            private Integer messageLike;

                        /**
         * 教师ID
         */
                    @TableField("tea_id")
                            private Long teaId;

                        /**
         * 评论数
         */
                    @TableField("comment_num")
                            private Integer commentNum;

                        /**
         * 逻辑删除
         */
                    @TableField("deleted")
                            private Boolean deleted;


        }

