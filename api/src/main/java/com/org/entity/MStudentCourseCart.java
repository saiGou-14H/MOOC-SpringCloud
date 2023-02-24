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
 * 购物车表

 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_student_course_cart")
public class MStudentCourseCart implements Serializable {

private static final long serialVersionUID = 1L;

                        /**
         * 学生id
         */
                    @TableField("stu_id")
                            private Long stuId;

                        /**
         * 课程id
         */
                    @TableField("cou_id")
                            private Long couId;

                        /**
         * 加入购物车时间
         */
                                        @TableField(value = "add_time", fill = FieldFill.INSERT)
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime addTime;


        }

