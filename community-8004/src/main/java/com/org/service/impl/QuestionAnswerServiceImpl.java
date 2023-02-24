package com.org.service.impl;

import com.org.model.QuestionAnswer;
import com.org.mapper.QuestionAnswerMapper;
import com.org.service.IQuestionAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 话题/问题表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-11-18
 */
@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswer> implements IQuestionAnswerService {

    @Autowired
    QuestionAnswerMapper questionAnswerMapper;

    @Override
    public boolean upQuestion(Map<String, Object> map) {
        return questionAnswerMapper.upQuestion(map);
    }



}
