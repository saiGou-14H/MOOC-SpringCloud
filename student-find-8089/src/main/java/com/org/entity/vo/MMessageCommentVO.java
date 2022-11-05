package com.org.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Jie
 * @Date 2022-11-04
 */
@Data
public class MMessageCommentVO implements Serializable {
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
     * 评论者ID
     */
    private Long userId;

    /**
     * 资讯ID
     */
    private Long messageId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */

    private String date;

    /**
     * 是否点赞
     */
    private Boolean isLike;
}
