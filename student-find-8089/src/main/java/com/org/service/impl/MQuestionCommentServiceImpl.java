package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MQuestionComment;
import com.org.entity.vo.MQuestionCommentVO;
import com.org.mapper.MQuestionCommentMapper;
import com.org.service.MQuestionCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
@Service
public class MQuestionCommentServiceImpl extends ServiceImpl<MQuestionCommentMapper, MQuestionComment> implements MQuestionCommentService {
    @Autowired
    private MQuestionCommentMapper mQuestionCommentMapper;
    @Override
    public List<MQuestionCommentVO> searchMQuestionCommentBy(Integer questionId) {
        return mQuestionCommentMapper.searchMQuestionCommentBy(questionId);
    }
}

