package com.org.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表

 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_student_course_order")
public class MStudentCourseOrder implements Serializable {

private static final long serialVersionUID = 1L;

                            /**
         * 订单编号，雪花ID
         */
                                        @TableId("id")
                                            private Long id;

                        /**
         * 学生id
         */
                    @TableField("stu_id")
                            private Long stuId;

                        /**
         * 支付状态
0未支付 1为已支付
         */
                    @TableField("pay_state")
                            private Boolean payState;

                        /**
         * 创建时间
         */
                                        @TableField(value = "create_time", fill = FieldFill.INSERT)
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

                        /**
         * 支付时间
         */
                                        @TableField(value = "pay_time", fill = FieldFill.INSERT)
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime payTime;

                        /**
         * 备注
         */
                    @TableField("remark")
                            private String remark;

                        /**
         * 课程总价
         */
                    @TableField("all_integral")
                            private Integer allIntegral;


        }

