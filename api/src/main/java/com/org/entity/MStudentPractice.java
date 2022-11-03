package com.org.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 学生实践关联表
 *
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
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
     * 报名时间
     */
    @TableField("pra_date")
    private LocalDateTime praDate;


}

