package com.org.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Jie
 * @Date 2022-11-03
 */
@Data
public class QuestionAnswerPageVO implements Serializable {
    /**
     * 评论者名称
     */
    private String username;
    /**
     * 评论者头像
     */
    private String headPic;

    /**
     * 雪花ID后八位
     */
    private Long id;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 发布问题/回复的作者id
     */
    private Long auId;

    /**
     * 内容
     */
    private String content;

    /**
     * 资源地址
     */
    private String resourceUrl;

    /**
     * 1被采纳,0否
     */
    private Boolean isSelect;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 创建时间
     */
    private String createTime;

}
