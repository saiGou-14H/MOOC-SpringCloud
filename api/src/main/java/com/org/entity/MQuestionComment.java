package com.org.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 *
 * </p>
 *
 * @author Jie
 * @since 2022-11-07
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
    private Integer id;

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
    @TableField("date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime date;

    /**
     * 点赞数
     */
    @TableField("like")
    private String like;

    /**
     * 是否最佳回答
     */
    @TableField("is_best")
    private Boolean isBest;


}

