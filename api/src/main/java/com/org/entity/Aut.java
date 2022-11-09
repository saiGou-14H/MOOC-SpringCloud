package com.org.entity;

        import com.baomidou.mybatisplus.annotation.TableName;
        import com.baomidou.mybatisplus.annotation.TableId;
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
        @TableName("aut")
public class Aut implements Serializable {

private static final long serialVersionUID = 1L;

                                        @TableId("id")
                                            private Long id;

                        /**
         * 雪花ID
         */
                    @TableField("aut_id")
                            private Long autId;

                @TableField("email")
                            private String email;

                @TableField("password")
                            private String password;

                @TableField("username")
                            private String username;

                        /**
         * 绑定微信授权
         */
                    @TableField("openid")
                            private String openid;


        }

