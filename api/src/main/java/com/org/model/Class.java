package com.org.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 班级表	
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@Getter
@Setter
@TableName("m_class")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)                //链式写法
@ApiModel(value = "Class对象", description = "班级表	")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("班级ID，雪花ID后8位")
    @TableId("id")
    private Long id;

    @ApiModelProperty("教师id,对应用户表")
    @TableField("tea_id")
    private Long teaId;

    @ApiModelProperty("班级名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("班级人数")
    @TableField("numbers")
    private Integer numbers;

    @ApiModelProperty("班级介绍")
    @TableField("introduction")
    private String introduction;

    @ApiModelProperty("默认值：0已删除：1")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

    @TableField(exist = false)
    private List<Course> courses;

}
