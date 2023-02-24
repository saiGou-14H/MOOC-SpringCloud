package com.org.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.org.model.QuestionComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionAnswerDTO1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("问题id")
    private Long qaId;

    @ApiModelProperty("问题标题")
    private String title;

    @ApiModelProperty("审核状态")
    private Integer isCheck;

    @ApiModelProperty("发布问题/回复的作者id")
    private Long auId;

    //问题发布者的姓名
    @TableField(exist = false)
    private String auName;

    //问题发布者的头像
    @TableField(exist = false)
    private String auHead;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("资源地址")
    private String resourceUrl;

    @ApiModelProperty("1被采纳,0否")
    private Boolean isSelect;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("点赞数")
    @TableField("like_num")
    private Integer likeNum;

    @ApiModelProperty("评论数")
    @TableField("comment_num")
    private Integer commentNum;

    //问题的回复
    @TableField(exist = false)
    List<QuestionComment> questionComments;

}
