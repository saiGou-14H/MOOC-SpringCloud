package com.org.mapper;

import com.org.model.StudentClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 学生班级关联表	 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-10-26
 */
@Mapper
public interface StudentClassMapper extends BaseMapper<StudentClass> {

    List<Long> shClassStu(Long cla_id);
}
