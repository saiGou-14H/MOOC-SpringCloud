package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.MStudentCourseCart;
import com.org.entity.vo.StudentCourseCartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 购物车表
 * Mapper 接口
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Mapper
public interface MStudentCourseCartMapper extends BaseMapper<MStudentCourseCart> {
    List<StudentCourseCartVO> searchStudentCourseCart(@Param("userId")Long userId, @Param("start")Long start, @Param("end") Long end);
}

