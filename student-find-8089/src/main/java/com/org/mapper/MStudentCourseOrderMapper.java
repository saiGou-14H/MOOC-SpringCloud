package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.MStudentCourseOrder;
import com.org.entity.vo.CourseVO;
import com.org.entity.vo.StudentOrderCourseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表
 * Mapper 接口
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
@Mapper
public interface MStudentCourseOrderMapper extends BaseMapper<MStudentCourseOrder> {
    public List<CourseVO> searchCourseList(List<Long> couIds);

    public List<StudentOrderCourseVO> searchOrderCourseByPage(Map<String, Object> map);
}

