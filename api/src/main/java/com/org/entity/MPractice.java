package com.org.entity;

        import com.baomidou.mybatisplus.annotation.TableName;
        import java.time.LocalDate;
        import com.baomidou.mybatisplus.annotation.TableId;
        import com.baomidou.mybatisplus.annotation.TableField;
        import java.io.Serializable;
                import lombok.Data;
    import lombok.EqualsAndHashCode;
            import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 线下实践表

 * </p>
 *
 * @author Jie
 * @since 2022-11-09
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_practice")
public class MPractice implements Serializable {

private static final long serialVersionUID = 1L;

                            /**
         * 实践id,雪花ID后八位
         */
                                        @TableId("id")
                                            private Long id;

                        /**
         * 实践名称
         */
                    @TableField("name")
                            private String name;

                        /**
         * 实践时间
         */
                    @TableField("date")
                            private LocalDate date;

                        /**
         * 实践地点
         */
                    @TableField("site")
                            private String site;

                        /**
         * 实践内容
         */
                    @TableField("content")
                            private String content;

                        /**
         * 教师ID
         */
                    @TableField("tea_id")
                            private Long teaId;

                        /**
         * 课程id
         */
                    @TableField("cou_id")
                            private Long couId;

                        /**
         * 是否删除
         */
                    @TableField("deleted")
                            private Boolean deleted;


        }

