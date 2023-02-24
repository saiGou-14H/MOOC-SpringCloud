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
 * 学习进度表	
 * </p>
 *
 * @author B.M
 * @since 2022-11-05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("m_learn_progress")
@ApiModel(value = "LearnProgress对象", description = "学习进度表	")
public class LearnProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学生id")
    @TableField("stu_id")
    private Long stuId;

    @ApiModelProperty("课程id")
    @TableField("cou_id")
    private Long couId;

    @ApiModelProperty("班级id")
    @TableField("class_id")
    private Long classId;

    @ApiModelProperty("章节id")
    @TableField("cha_id")
    private Integer chaId;

    @ApiModelProperty("章节在课程中的序号")
    @TableField("cha_index")
    private Integer chaIndex;

    @ApiModelProperty("记录更新的时间")
    @TableField("update_time")
    private String updateTime;

    @ApiModelProperty("最新开始进度条时间")
    @TableField("start_time")
    private String startTime;

    @ApiModelProperty("最新章节看到的时间")
    @TableField("end_time")
    private String endTime;

    @ApiModelProperty("实际已完成观看时长")
    @TableField("read_time")
    private String readTime;

    @ApiModelProperty("今日观看时长")
    @TableField("day_time")
    private String dayTime;


}
