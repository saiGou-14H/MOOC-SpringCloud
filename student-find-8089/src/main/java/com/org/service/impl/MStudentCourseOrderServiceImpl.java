package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MStudentCourseOrder;
import com.org.entity.vo.CourseVO;
import com.org.entity.vo.StudentOrderCourseVO;
import com.org.mapper.MStudentCourseOrderMapper;
import com.org.service.MStudentCourseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表
 * 服务实现类
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
@Service
public class MStudentCourseOrderServiceImpl extends ServiceImpl<MStudentCourseOrderMapper, MStudentCourseOrder> implements MStudentCourseOrderService {
    @Autowired
    private MStudentCourseOrderMapper mStudentCourseOrderMapper;

    @Override
    public boolean addOrder(MStudentCourseOrder mStudentCourseOrder) {
        this.save(mStudentCourseOrder);
        return true;
    }

    @Override
    public List<CourseVO> searchCourseList(List<Long> couIds) {
        return mStudentCourseOrderMapper.searchCourseList(couIds);
    }

    @Override
    public List<StudentOrderCourseVO> searchOrderCourseByPage(Map<String, Object> map) {
        return mStudentCourseOrderMapper.searchOrderCourseByPage(map);
    }
}

