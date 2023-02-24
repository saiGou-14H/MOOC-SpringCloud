package com.org.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 章节表	
 * </p>
 *
 * @author B.M
 * @since 2022-11-05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("m_course_chapter")
@ApiModel(value = "CourseChapter对象", description = "章节表	")
public class CourseChapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("章节id,自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("章节排序序号")
    @TableField("cha_index")
    private Integer chaIndex;

    @ApiModelProperty("章节标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("章节内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("课程id")
    @TableField("course_id")
    private Long courseId;

    @ApiModelProperty("资源地址")
    @TableField("resource_url")
    private String resourceUrl;

    @ApiModelProperty("章节时长")
    @TableField("time")
    private Long time;

    @ApiModelProperty("乐观锁，默认为0")
    @TableField("version")
    @Version
    private Integer version;


}
