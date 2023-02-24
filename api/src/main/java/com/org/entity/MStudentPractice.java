package com.org.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 学生实践关联表

 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_student_practice")
public class MStudentPractice implements Serializable {

private static final long serialVersionUID = 1L;

                        /**
         * 学生id
         */
                    @TableField("stu_id")
                            private Long stuId;

                        /**
         * 实践id
         */
                    @TableField("pra_id")
                            private Long praId;

                        /**
         * 实践的时间
         */
                    @TableField("pra_date")
                            private LocalDate praDate;

                        /**
         * 报名姓名
         */
                    @TableField("stu_name")
                            private String stuName;

                        /**
         * 报名的电话号码
         */
                    @TableField("phone")
                            private String phone;

                        /**
         * 报名的时间
         */
                                        @TableField(value = "time", fill = FieldFill.INSERT)
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime time;

                        /**
         * 实践的地点
         */
                    @TableField("position")
                            private String position;

                        /**
         * 备注
         */
                    @TableField("remake")
                            private String remake;


        }

