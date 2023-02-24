package com.org.service.impl;

import com.org.model.QuestionComment;
import com.org.mapper.QuestionCommentMapper;
import com.org.service.IQuestionCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-11-18
 */
@Service
public class QuestionCommentServiceImpl extends ServiceImpl<QuestionCommentMapper, QuestionComment> implements IQuestionCommentService {

    @Autowired
    QuestionCommentMapper questionCommentMapper;

    @Override
    public boolean answerQue(QuestionComment questionComment) {
        return questionCommentMapper.answerQue(questionComment);
    }

    @Override
    public boolean acceptCom(Long id) {
        return questionCommentMapper.acceptCom(id);
    }

    @Override
    public boolean checkCom(Map<String, Object> map) {
        return questionCommentMapper.checkCom(map);
    }
}
