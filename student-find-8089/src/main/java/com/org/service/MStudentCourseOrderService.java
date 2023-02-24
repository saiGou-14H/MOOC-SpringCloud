package com.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.entity.MStudentCourseOrder;
import com.org.entity.vo.CourseVO;
import com.org.entity.vo.StudentOrderCourseVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表
 * 服务类
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
public interface MStudentCourseOrderService extends IService<MStudentCourseOrder> {
    boolean addOrder(MStudentCourseOrder mStudentCourseOrder);
    List<CourseVO> searchCourseList(List<Long> couIds);

    List<StudentOrderCourseVO> searchOrderCourseByPage(Map<String, Object> map);
}

