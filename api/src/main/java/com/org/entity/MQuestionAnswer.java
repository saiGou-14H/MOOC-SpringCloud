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
 * 话题/问题表

 * </p>
 *
 * @author Jie
 * @since 2022-11-09
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_question_answer")
public class MQuestionAnswer implements Serializable {

private static final long serialVersionUID = 1L;

                            /**
         * 雪花ID后八位
         */
                                        @TableId("id")
                                            private Long id;

                        /**
         * 发布问题/回复的作者id
         */
                    @TableField("au_id")
                            private Long auId;

                        /**
         * 问题标题
         */
                    @TableField("title")
                            private String title;

                        /**
         * 内容
         */
                    @TableField("content")
                            private String content;

                        /**
         * 资源地址
         */
                    @TableField("resource_url")
                            private String resourceUrl;

                        /**
         * 创建时间
         */
                                        @TableField(value = "create_time", fill = FieldFill.INSERT)
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

                        /**
         * 0问题/1回复
         */
                    @TableField("is_question")
                            private Boolean isQuestion;

                        /**
         * 自关联到本表的id字段
         */
                    @TableField("answer_qa_id")
                            private Long answerQaId;

                        /**
         * 1被采纳,0否
         */
                    @TableField("is_select")
                            private Boolean isSelect;

                        /**
         * ip地址
         */
                    @TableField("ip")
                            private String ip;


        }

