package com.org.mapper;

import com.org.model.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.model.dto.ClassDTO1;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 班级表	 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@Mapper
public interface ClassMapper extends BaseMapper<Class> {

    public boolean updateCourse(Class classData);

    public List<Class> shClass(Long class_id);

    public List<Class> searchAllClass(Long tea_id);

    public List<ClassDTO1> shQueCom(Map<String, Long> map);
}
