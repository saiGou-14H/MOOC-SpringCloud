package com.org.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程类别字典表	
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("m_course_type")
@ApiModel(value = "CourseType对象", description = "课程类别字典表	")
public class CourseType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("课程类别")
    @TableField("type")
    private String type;

    @ApiModelProperty("父类别ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("类型封面图片")
    @TableField("picture")
    private String picture;

    @TableField(exist = false)
    private List<CourseType> courseTypeList;


}
