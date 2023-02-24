package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.MCourseType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ISCourseTypeMapper extends BaseMapper<MCourseType> {
    @Select("SELECT id,type,parent_id,picture FROM m_course_type WHERE (parent_id is Null)")
    public List<MCourseType> shMaxType();
}
