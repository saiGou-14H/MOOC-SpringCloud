package com.org.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Jie
 * @Date 2022-11-04
 */
@Data
public class MQuestionCommentVO implements Serializable {
    /**
     * 评论id（评论下再评论时才用到）
     */
    private Integer id;
    /**
     * 评论者名称
     */
    private String username;
    /**
     * 评论者头像
     */
    private String headPic;


    /**
     * 回复者ID
     */
    private Long userId;

    /**
     * 问题ID
     */
    private Long questionId;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 回复时间
     */
    private String date;

    /**
     * 点赞数
     */
    private String like;

    /**
     * 是否最佳回答
     */
    private Boolean isBest;
}
