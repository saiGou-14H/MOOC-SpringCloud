package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.MQuestionAnswer;
import com.org.entity.vo.QuestionAnswerPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 话题/问题表
 * Mapper 接口
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
@Mapper
public interface MQuestionAnswerMapper extends BaseMapper<MQuestionAnswer> {

    public List<QuestionAnswerPageVO> searchmQuestionAnswerByPage(@Param("start") Integer start, @Param("end") Integer end);

    public List<QuestionAnswerPageVO> searchMyMQuestionAnswerByPage(@Param("userId") Long userId, @Param("start") Long start, @Param("end") Long end);

    public QuestionAnswerPageVO searchMQuestionAnswerById(@Param("questionId") Integer questionId);
}

