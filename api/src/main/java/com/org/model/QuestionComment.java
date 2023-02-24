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
 * 
 * </p>
 *
 * @author B.M
 * @since 2022-11-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("m_question_comment")
@ApiModel(value = "QuestionComment对象", description = "")
public class QuestionComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("回复ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("回复者ID")
    @TableField("user_id")
    private Long userId;

    //问题回复者的姓名
    @TableField(exist = false)
    private String userName;

    //问题回复者的头像
    @TableField(exist = false)
    private String userHead;

    @ApiModelProperty("问题ID")
    @TableField("question_id")
    private Long questionId;

    @ApiModelProperty("回复内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("回复时间")
    @TableField(value = "date", fill = FieldFill.INSERT)
    private String date;

    @ApiModelProperty("点赞数")
    @TableField("like_num")
    private Integer likeNum;

    @ApiModelProperty("是否最佳回答")
    @TableField("is_best")
    private Integer isBest;
}
