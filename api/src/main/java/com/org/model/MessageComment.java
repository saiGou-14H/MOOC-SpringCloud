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
 * 资讯评论表	
 * </p>
 *
 * @author B.M
 * @since 2022-11-03
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("m_message_comment")
@ApiModel(value = "MessageComment对象", description = "资讯评论表	")
public class MessageComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论id（评论下再评论时才用到）")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("评论者ID")
    @TableField("c_id")
    private Long cId;

    @ApiModelProperty("资讯ID")
    @TableField("message_id")
    private Long messageId;

    @ApiModelProperty("评论内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("评论时间")
    @TableField("date")
    private LocalDateTime date;

    @ApiModelProperty("是否点赞")
    @TableField("is_like")
    private Boolean isLike;

    @ApiModelProperty("是否删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

}
