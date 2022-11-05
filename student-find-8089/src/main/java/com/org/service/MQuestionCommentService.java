package com.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.entity.MQuestionComment;
import com.org.entity.vo.MQuestionCommentVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
public interface MQuestionCommentService extends IService<MQuestionComment> {

    public List<MQuestionCommentVO> searchMQuestionCommentBy(Integer questionId);
}

