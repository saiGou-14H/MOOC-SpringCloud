package com.org.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生课程关联表	
 * </p>
 *
 * @author B.M
 * @since 2022-10-31
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("m_student_course")
@ApiModel(value = "StudentCourse对象", description = "学生课程关联表	")
public class StudentCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学生id")
    @TableField("stu_id")
    private Long stuId;

    @ApiModelProperty("课程id")
    @TableField("cou_id")
    private Long couId;

    @ApiModelProperty("是否收藏	0为未收藏1为已收藏")
    @TableField("collect")
    private Boolean collect;

    @ApiModelProperty("是否购买	0为未购买1为已购买")
    @TableField("have")
    private Boolean have;

    @ApiModelProperty("是否点赞	0为未点赞1为已点赞")
    @TableField("recommend")
    private Boolean recommend;

    @ApiModelProperty("课程评价")
    @TableField("comment")
    private String comment;


}
