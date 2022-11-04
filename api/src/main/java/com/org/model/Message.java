package com.org.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资讯表	
 * </p>
 *
 * @author B.M
 * @since 2022-11-03
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("m_message")
@ApiModel(value = "Message对象", description = "资讯表	")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("资讯id（雪花id）")
    @TableId("id")
    private Long id;

    @ApiModelProperty("资讯标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("资讯内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("资源地址")
    @TableField("resource_url")
    private String resourceUrl;

    @ApiModelProperty("资讯作者")
    @TableField("author")
    private String author;

    @ApiModelProperty("发布日期")
    @TableField(value = "date", fill = FieldFill.INSERT)
    private LocalDateTime date;

    @ApiModelProperty("点赞数")
    @TableField("message_like")
    private Integer messageLike;

    @ApiModelProperty("教师ID")
    @TableField("tea_id")
    private Long teaId;

    @ApiModelProperty("评论数")
    @TableField("comment_num")
    private Integer commentNum;

    @ApiModelProperty("是否删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

}
