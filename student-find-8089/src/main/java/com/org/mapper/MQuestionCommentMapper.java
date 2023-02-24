package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.MQuestionComment;
import com.org.entity.vo.MQuestionCommentVO;
import com.org.entity.vo.MyMQuestionCommentVO;
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
    public List<MyMQuestionCommentVO> searchMyMQuestionCommentByPage(@Param("userId") Long userId, @Param("start") Long start, @Param("end") Long end);
    public int updateQuestionAnswerCommentNum(@Param("commentNum") Long commentNum, @Param("id") Long id);
    public int takeQuestionComment(@Param("isBest") Long isBest, @Param("id") Long id);
}

