package com.org.service;

import com.org.model.QuestionAnswer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 话题/问题表	 服务类
 * </p>
 *
 * @author B.M
 * @since 2022-11-18
 */
public interface IQuestionAnswerService extends IService<QuestionAnswer> {

    public boolean upQuestion(Map<String, Object> map);
}
