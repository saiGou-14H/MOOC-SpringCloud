package com.org.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Jie
 * @Date 2022-11-03
 */
@Data
public class MessagePageVO implements Serializable {
    /**
     * 资讯作者名称
     */
    private String username;
    /**
     * 资讯作者头像
     */
    private String headPic;

    /**
     * 资讯id（雪花id）
     */
    private Long id;

    /**
     * 资讯标题
     */
    private String title;

    /**
     * 资讯内容
     */
    private String content;

    /**
     * 资源地址
     */
    private String resourceUrl;

    /**
     * 资讯作者
     */
    private String author;

    /**
     * 发布日期
     */
    private String date;

    /**
     * 点赞数
     */
    private Integer messageLike;

    /**
     * 教师ID
     */
    private Long teaId;

    /**
     * 评论数
     */
    private Integer commentNum;
}
