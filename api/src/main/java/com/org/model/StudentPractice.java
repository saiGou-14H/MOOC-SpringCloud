package com.org.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生实践关联表	
 * </p>
 *
 * @author B.M
 * @since 2022-10-28
 */
@Data
@Accessors(chain = true)
@TableName("m_student_practice")
@ApiModel(value = "StudentPractice对象", description = "学生实践关联表	")
public class StudentPractice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学生id")
    @TableField("stu_id")
    private Long stuId;

    @ApiModelProperty("实践id")
    @TableField("pra_id")
    private Long praId;

    @ApiModelProperty("报名时间")
    @TableField(value = "pra_date", fill = FieldFill.INSERT)
    private String praDate;


}
