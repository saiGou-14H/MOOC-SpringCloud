package com.org.model.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api("学生各课程学习进度")
public class UserDTO2 {

    @ApiModelProperty("课程id")
    private Long id;

    @ApiModelProperty("课程名称")
    private String name;

    @ApiModelProperty("课程时长")
    private Long courseTime;

    @ApiModelProperty("学习时长")
    private Long learned;

}
