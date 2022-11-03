package com.org.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 班级课程关联表	
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("m_class_course")
@ApiModel(value = "ClassCourse对象", description = "班级课程关联表	")
public class ClassCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程id")
    @TableField("cou_id")
    private Long couId;

    @ApiModelProperty("班级id")
    @TableField("class_id")
    private Long classId;

    @ApiModelProperty("加入时间")
    @TableField(value = "join_date", fill = FieldFill.INSERT)       //自动填充时间
    private LocalDateTime joinDate;


}
