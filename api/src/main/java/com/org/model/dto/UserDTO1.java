package com.org.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表

 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@Data
@Api("学生信息以及在班级中总学习进度")
public class UserDTO1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("班级ID，雪花ID后8位")
    @TableId("id")
    private Long id;

    @ApiModelProperty("真实姓名")
    @TableField("username")
    private String username;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("qq邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("手机号码")
    @TableField("phone")
    private String phone;

    @TableField(exist = false)
    private List<TimeDTO> learned;

    @ApiModelProperty("学习时长")
    @TableField("study_time")
    private Long studyTime;

    @ApiModelProperty("学习积分")
    @TableField("integral")
    private Integer integral;

    @ApiModelProperty("职务")
    @TableField("position")
    private String position;

    @ApiModelProperty("头像")
    @TableField("head_pic")
    private String headPic;

    @ApiModelProperty("人脸")
    @TableField("face_pic")
    private String facePic;

    @ApiModelProperty("个人介绍")
    @TableField("introduction")
    private String introduction;

    @ApiModelProperty("周打卡，默认为7个0")
    @TableField("sign")
    private String sign;


}
