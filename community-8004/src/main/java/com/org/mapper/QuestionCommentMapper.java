package com.org.mapper;

import com.org.model.QuestionComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-11-18
 */
@Mapper
public interface QuestionCommentMapper extends BaseMapper<QuestionComment> {

    public boolean answerQue(QuestionComment questionComment);

    public boolean acceptCom(Long queId);

    public boolean checkCom(Map<String, Object> map);
}
