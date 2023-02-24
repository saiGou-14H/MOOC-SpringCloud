package com.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.entity.MQuestionAnswer;
import com.org.entity.vo.QuestionAnswerPageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 话题/问题表
 * 服务类
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
public interface MQuestionAnswerService extends IService<MQuestionAnswer> {
    List<QuestionAnswerPageVO> searchmQuestionAnswerByPage(Integer start, Integer end);

    List<QuestionAnswerPageVO> searchMyMQuestionAnswerByPage(Long userId, Long start, Long end);

    QuestionAnswerPageVO searchMQuestionAnswerById(@Param("questionId") Integer questionId);
}

