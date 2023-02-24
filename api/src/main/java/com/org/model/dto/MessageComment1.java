package com.org.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageComment1 {
    //资讯评论id
    private Long id;
    //评论者姓名
    private String cName;
    //评论内容
    private String content;
    //评论日期
    private LocalDateTime date;
    //是否点赞
    private int isLike;
}
