package com.org.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 资讯评论表

 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_message_comment")
public class MMessageComment implements Serializable {

private static final long serialVersionUID = 1L;

                            /**
         * 评论id（评论下再评论时才用到）
         */
                                        @TableId(value = "id", type = IdType.AUTO)
                                            private Integer id;

                        /**
         * 评论者ID
         */
                    @TableField("user_id")
                            private Long userId;

                        /**
         * 资讯ID
         */
                    @TableField("message_id")
                            private Long messageId;

                        /**
         * 评论内容
         */
                    @TableField("content")
                            private String content;

                        /**
         * 评论时间
         */
                                        @TableField(value = "date", fill = FieldFill.INSERT)
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime date;

                        /**
         * 是否点赞
         */
                    @TableField("is_like")
                            private Boolean isLike;

                        /**
         * 是否删除
         */
                    @TableField("deleted")
                            private Boolean deleted;


        }

