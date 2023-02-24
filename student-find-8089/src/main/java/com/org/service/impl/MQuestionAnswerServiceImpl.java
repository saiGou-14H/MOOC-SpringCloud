package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MQuestionAnswer;
import com.org.entity.vo.QuestionAnswerPageVO;
import com.org.mapper.MQuestionAnswerMapper;
import com.org.service.MQuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 话题/问题表
 * 服务实现类
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
@Service
public class MQuestionAnswerServiceImpl extends ServiceImpl<MQuestionAnswerMapper, MQuestionAnswer> implements MQuestionAnswerService {
    @Autowired
    private MQuestionAnswerMapper questionAnswerMapper;

    @Override
    public List<QuestionAnswerPageVO> searchmQuestionAnswerByPage(Integer start, Integer end) {
        return questionAnswerMapper.searchmQuestionAnswerByPage(start, end);
    }

    @Override
    public List<QuestionAnswerPageVO> searchMyMQuestionAnswerByPage(Long userId, Long start, Long end) {
        return questionAnswerMapper.searchMyMQuestionAnswerByPage(userId, start, end);
    }

    @Override
    public QuestionAnswerPageVO searchMQuestionAnswerById(Integer questionId) {
        return questionAnswerMapper.searchMQuestionAnswerById(questionId);
    }
}

