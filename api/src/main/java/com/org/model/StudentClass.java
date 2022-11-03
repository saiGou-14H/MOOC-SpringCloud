package com.org.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生班级关联表	
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)                //链式写法
@TableName("m_student_class")
@ApiModel(value = "StudentClass对象", description = "学生班级关联表	")
public class StudentClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学生id")
    @TableField("stu_id")
    private Long stuId;

    @ApiModelProperty("班级id")
    @TableField("class_id")
    private Long classId;

    @ApiModelProperty("加入时间")
    @TableField(value = "join_date", fill = FieldFill.INSERT)       //自动填充时间
    private LocalDateTime joinDate;


}
