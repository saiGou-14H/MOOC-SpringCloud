package com.org.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 线下实践表	
 * </p>
 *
 * @author B.M
 * @since 2022-10-28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("m_practice")
@ApiModel(value = "Practice对象", description = "线下实践表	")
public class Practice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("实践id,雪花ID后八位")
    @TableId("id")
    private Long id;

    @ApiModelProperty("实践名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("实践时间")
    @TableField("date")
    private String date;

    @ApiModelProperty("实践地点")
    @TableField("site")
    private String site;

    @ApiModelProperty("实践内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("教师ID")
    @TableField("tea_id")
    private Long teaId;

    @ApiModelProperty("课程id")
    @TableField("cou_id")
    private Long couId;

    @ApiModelProperty("是否删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

}
