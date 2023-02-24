package com.org.service;

import com.org.model.QuestionComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author B.M
 * @since 2022-11-18
 */
public interface IQuestionCommentService extends IService<QuestionComment> {

    public boolean answerQue(QuestionComment questionComment);

    public boolean acceptCom(Long id);

    public boolean checkCom(Map<String, Object> map);
}
