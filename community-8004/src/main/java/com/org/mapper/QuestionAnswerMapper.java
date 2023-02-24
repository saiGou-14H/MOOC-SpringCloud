package com.org.mapper;

import com.org.model.QuestionAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 * 话题/问题表	 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-11-18
 */
@Mapper
public interface QuestionAnswerMapper extends BaseMapper<QuestionAnswer> {

    public boolean upQuestion(Map<String, Object> map);
}
