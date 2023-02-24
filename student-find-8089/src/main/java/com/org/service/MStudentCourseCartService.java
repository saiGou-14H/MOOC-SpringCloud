package com.org.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.org.entity.MStudentCourseCart;
import com.org.entity.vo.StudentCourseCartVO;

import java.util.List;

/**
 * <p>
 * 购物车表
 * 服务类
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
public interface MStudentCourseCartService extends IService<MStudentCourseCart> {
    List<StudentCourseCartVO> searchStudentCourseCart(Long userId, Long start, Long end);
}

