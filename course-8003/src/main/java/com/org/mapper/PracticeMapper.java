package com.org.mapper;

import com.org.model.Practice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.model.dto.PracticeDTO1;
import com.org.model.vo.Practice1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 线下实践表	 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-10-28
 */
@Mapper
public interface PracticeMapper extends BaseMapper<Practice> {

    public boolean udPractice(Practice practice);

    public List<String> shPracticeIds(Long course);

    public List<PracticeDTO1> shPractice1(Practice1 practice1);

    public List<Practice> shPractice2(HashMap<String,Long> params);

    public List<Practice> shPractice3(Long tea_id);

}
