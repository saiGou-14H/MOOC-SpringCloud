package com.org.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("m_user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("班级ID，雪花ID后8位")
    @TableId("id")
    private Long id;

    @ApiModelProperty("真实姓名")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("1为学生，2为教师	默认为学生")
    @TableField("role")
    private Integer role;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("qq邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("手机号码")
    @TableField("phone")
    private String phone;

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
