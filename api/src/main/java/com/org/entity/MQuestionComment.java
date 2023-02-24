package com.org.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_question_comment")
public class MQuestionComment implements Serializable {

private static final long serialVersionUID = 1L;

                            /**
         * 回复ID
         */
                                        @TableId(value = "id", type = IdType.AUTO)
                                            private Long id;

                        /**
         * 回复者ID
         */
                    @TableField("user_id")
                            private Long userId;

                        /**
         * 问题ID
         */
                    @TableField("question_id")
                            private Long questionId;

                        /**
         * 回复内容
         */
                    @TableField("content")
                            private String content;

                        /**
         * 回复时间
         */
                                        @TableField(value = "date", fill = FieldFill.INSERT)
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime date;

                        /**
         * 点赞数
         */
                    @TableField("like_num")
                            private Integer likeNum;

                        /**
         * 是否最佳回答
         */
                    @TableField("is_best")
                            private Boolean isBest;

                        /**
         * 逻辑删除
         */
                    @TableField("deleted")
                            private Boolean deleted;

                        /**
         * 是否审核
         */
                    @TableField("is_check")
                            private Boolean isCheck;


        }

