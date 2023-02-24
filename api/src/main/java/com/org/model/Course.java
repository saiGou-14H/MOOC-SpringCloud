package com.org.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 课程表	
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@Getter
@Setter
@Accessors(chain = true)                //链式写法
@TableName("m_course")
@ApiModel(value = "Course对象", description = "课程表")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程ID，雪花ID后8位")
    @TableId("id")
    private Long id;

    @ApiModelProperty("课程名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("课程作者")
    @TableField("tea_id")
    private Long teaId;

    @TableField(exist = false)
    private String author;

    @ApiModelProperty("课程类别")
    @TableField("cou_type")
    private String couType;

    @ApiModelProperty("课程积分（价格）")
    @TableField("integral")
    private Integer integral;

    @ApiModelProperty("课程介绍")
    @TableField("introduction")
    private String introduction;

    @ApiModelProperty("课程章节数")
    @TableField("cata_num")
    private Integer cataNum;

    @ApiModelProperty("课程收藏数")
    @TableField("coll_num")
    private Integer collNum;

    @ApiModelProperty("课程推荐数")
    @TableField("reco_num")
    private Integer recoNum;

    @ApiModelProperty("课程封面")
    @TableField("picture")
    private String picture;

    @ApiModelProperty("课程分享数")
    @TableField("share")
    private Integer share;

    @ApiModelProperty("乐观锁，默认为0")
    @TableField("version")
    @Version
    private Integer version;

    @ApiModelProperty("课程时长")
    @TableField("time")
    private Long time;

    @ApiModelProperty("逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

}
