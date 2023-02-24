package com.org.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_integral_history")
public class MIntegralHistory implements Serializable {

private static final long serialVersionUID = 1L;

                        /**
         * 学生ID
         */
                    @TableField("stu_id")
                            private Long stuId;

                        /**
         * 积分来源（签到/课程章节/分享/点赞/收藏）
         */
                    @TableField("origin")
                            private String origin;

                        /**
         * 获得积分数
         */
                    @TableField("integralchild")
                            private Integer integralchild;

                        /**
         * 类型（1获得/0扣除）
         */
                    @TableField("type")
                            private Boolean type;

                        /**
         * 获取时间
         */
                                        @TableField(value = "time", fill = FieldFill.INSERT)
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime time;


        }

