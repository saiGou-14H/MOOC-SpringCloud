package com.org.entity;

        import com.baomidou.mybatisplus.annotation.TableName;
        import com.baomidou.mybatisplus.annotation.TableId;
        import java.time.LocalDateTime;
        import com.baomidou.mybatisplus.annotation.FieldFill;
        import com.baomidou.mybatisplus.annotation.TableField;
        import java.io.Serializable;
                import lombok.Data;
    import lombok.EqualsAndHashCode;
            import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jie
 * @since 2022-11-09
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_integral_history")
public class MIntegralHistory implements Serializable {

private static final long serialVersionUID = 1L;

                            /**
         * 学生ID
         */
                                        @TableId("stu_id")
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

