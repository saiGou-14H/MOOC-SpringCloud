package com.org.ResponseVo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class CourseVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 课程ID，雪花ID后8位
     */
    @TableId("id")
    private Long id;

    /**
     * 课程名称
     */
    @TableField("name")
    private String name;

    /**
     * 课程作者
     */
    @TableField("tea_id")
    private Long teaId;

    /**
     * 课程类别
     */
    @TableField("cou_type")
    private String couType;

    /**
     * 课程积分（价格）
     */
    @TableField("integral")
    private Integer integral;

    /**
     * 课程介绍
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 课程章节数
     */
    @TableField("cata_num")
    private Integer cataNum;

    /**
     * 课程收藏数
     */
    @TableField("coll_num")
    private Integer collNum;

    /**
     * 课程点赞数
     */
    @TableField("reco_num")
    private Integer recoNum;

    /**
     * 课程封面图片
     */
    @TableField("picture")
    private String picture;

    /**
     * 课程分享数
     */
    @TableField("share")
    private Integer share;

    /**
     * 乐观锁，默认为0
     */
    @TableField("version")
    private Integer version;

    /**
     * 课程总时长（秒）
     */
    @TableField("time")
    private Long time;

    @TableField("username")
    private String username;
    @TableField("head_pic")
    private String headPic;
    @TableField("face_pic")
    private String facePic;

}

