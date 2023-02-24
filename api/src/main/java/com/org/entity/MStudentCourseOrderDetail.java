package com.org.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 订单明细表

 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_student_course_order_detail")
public class MStudentCourseOrderDetail implements Serializable {

private static final long serialVersionUID = 1L;

                            /**
         * 雪花ID
         */
                                        @TableId("id")
                                            private Long id;

                        /**
         * 课程id
         */
                    @TableField("course_id")
                            private Long courseId;

                        /**
         * 订单id
         */
                    @TableField("order_id")
                            private Long orderId;

                        /**
         * 课程价格
         */
                    @TableField("price")
                            private Integer price;


        }

