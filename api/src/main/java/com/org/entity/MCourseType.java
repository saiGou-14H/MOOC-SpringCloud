package com.org.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 课程类别字典表

 * </p>
 *
 * @author Jie
 * @since 2022-12-03
 */
@Data
        @EqualsAndHashCode(callSuper = false)
        @TableName("m_course_type")
public class MCourseType implements Serializable {

private static final long serialVersionUID = 1L;

                                        @TableId("id")
                                            private Integer id;

                        /**
         * 课程类别
         */
                    @TableField("type")
                            private String type;

                        /**
         * 父类别ID
         */
                    @TableField("parent_id")
                            private Integer parentId;

                        /**
         * 类型封面图片
         */
                    @TableField("picture")
                            private String picture;


        }

