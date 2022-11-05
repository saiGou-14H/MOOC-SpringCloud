package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MStudentCourseCart;
import com.org.entity.vo.StudentCourseCartVo;
import com.org.mapper.MStudentCourseCartMapper;
import com.org.service.MStudentCourseCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 购物车表
 * 服务实现类
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Service
public class MStudentCourseCartServiceImpl extends ServiceImpl<MStudentCourseCartMapper, MStudentCourseCart> implements MStudentCourseCartService {
    @Autowired
    private MStudentCourseCartMapper mStudentCourseCartMapper;

    @Override
    public List<StudentCourseCartVo> searchStudentCourseCart(Long userId, Long start, Long end) {
        return mStudentCourseCartMapper.searchStudentCourseCart(userId,start,end);
    }
}

