package com.org.entity;

        import com.baomidou.mybatisplus.annotation.TableName;
        import java.time.LocalDateTime;
        import com.baomidou.mybatisplus.annotation.FieldFill;
        import com.baomidou.mybatisplus.annotation.TableField;
        import java.io.Serializable;
                import lombok.Data;
    import lombok.EqualsAndHashCode;
            import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 学生课程关联表

 * </p>
 *
 * @author Jie
 * @since 2022-11-09
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_student_course")
public class MStudentCourse implements Serializable {

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
         * 是否收藏
0为未收藏1为已收藏
         */
                    @TableField("collect")
                            private Boolean collect;

                        /**
         * 是否购买
0为未购买1为已购买
         */
                    @TableField("have")
                            private Boolean have;

                        /**
         * 是否点赞
0为未点赞1为已点赞
         */
                    @TableField("recommend")
                            private Boolean recommend;

                        /**
         * 课程评论
         */
                    @TableField("comment")
                            private String comment;

                        /**
         * 课程评论时间
         */
                                        @TableField(value = "comment_time", fill = FieldFill.INSERT)
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime commentTime;


        }

