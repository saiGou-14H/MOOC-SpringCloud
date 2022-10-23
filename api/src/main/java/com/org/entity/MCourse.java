package com.org.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 课程表
 *
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_course")
public class MCourse implements Serializable {

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
     * 课程推荐数
     */
    @TableField("reco_num")
    private Integer recoNum;

    /**
     * 乐观锁，默认为0
     */
    @TableField("version")
    private Integer version;


}

