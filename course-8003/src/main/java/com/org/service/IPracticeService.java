package com.org.service;

import com.org.model.Practice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.org.model.dto.PracticeDTO1;
import com.org.model.vo.Practice1;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 线下实践表	 服务类
 * </p>
 *
 * @author B.M
 * @since 2022-10-28
 */
public interface IPracticeService extends IService<Practice> {

    public List<PracticeDTO1> shPractice1(Practice1 practice1);

    public List<Practice> shPractice2(HashMap<String,Long> params);

    public List<Practice> shPractice3(Long tea_id);

    public boolean udPractice(Practice practice);

    public List<String> shPracticeIds(Long course);
}
