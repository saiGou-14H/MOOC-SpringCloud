package com.org.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 资讯评论表
 *
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
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
    @TableField("c_id")
    private Long cId;

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
    @TableField("date")
    private String date;

    /**
     * 是否点赞
     */
    @TableField("is_like")
    private Boolean isLike;


}

