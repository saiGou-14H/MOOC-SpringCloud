package com.org.service.impl;

import com.org.model.Practice;
import com.org.mapper.PracticeMapper;
import com.org.model.dto.PracticeDTO1;
import com.org.model.vo.Practice1;
import com.org.service.IPracticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 线下实践表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-10-28
 */
@Service
public class PracticeServiceImpl extends ServiceImpl<PracticeMapper, Practice> implements IPracticeService {

    @Autowired
    PracticeMapper practiceMapper;

    @Override
    public List<String> shPracticeIds(Long course) {
        return practiceMapper.shPracticeIds(course);
    }

    @Override
    public List<PracticeDTO1> shPractice1(Practice1 practice1) {
        return practiceMapper.shPractice1(practice1);
    }

    @Override
    public List<Practice> shPractice2(HashMap<String,Long> params) {
        System.out.println(params.get("cou_id"));
        return practiceMapper.shPractice2(params);
    }

    @Override
    public List<Practice> shPractice3(Long tea_id) {
        return practiceMapper.shPractice3(tea_id);
    }

    @Override
    public boolean udPractice(Practice practice) {
        return practiceMapper.udPractice(practice);
    }
}
