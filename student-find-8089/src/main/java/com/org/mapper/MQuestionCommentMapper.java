package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.MQuestionComment;
import com.org.entity.vo.MQuestionCommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Jie
 * @since 2022-11-06
 */
@Mapper
public interface MQuestionCommentMapper extends BaseMapper<MQuestionComment> {
    public List<MQuestionCommentVO> searchMQuestionCommentBy(@Param("questionId")Integer questionId);
}

