package com.org.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 话题/问题表	
 * </p>
 *
 * @author B.M
 * @since 2022-11-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("m_question_answer")
@ApiModel(value = "QuestionAnswer对象", description = "话题/问题表	")
public class QuestionAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("雪花ID后八位")
    @TableId("id")
    private Long id;

    @ApiModelProperty("问题标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("发布问题/回复的作者id")
    @TableField("au_id")
    private Long auId;

    @ApiModelProperty("内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("课程id")
    @TableField("cou_id")
    private Long couId;

    @ApiModelProperty("班级id")
    @TableField("cla_id")
    private Long claId;

    @ApiModelProperty("资源地址")
    @TableField("resource_url")
    private String resourceUrl;

    @ApiModelProperty("1被采纳,0否")
    @TableField("is_select")
    private Integer isSelect;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("点赞数")
    @TableField("like_num")
    private Integer likeNum;

    @ApiModelProperty("评论数")
    @TableField("comment_num")
    private Integer commentNum;

    @ApiModelProperty("是否审核")
    @TableField("is_check")
    private Integer isCheck;

    @ApiModelProperty("逻辑删除")
    @TableField("deleted")
    private Integer deleted;

}
