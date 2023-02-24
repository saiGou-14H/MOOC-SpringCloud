package com.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.entity.MQuestionComment;
import com.org.entity.vo.MQuestionCommentVO;
import com.org.entity.vo.MyMQuestionCommentVO;
import org.apache.ibatis.annotations.Param;

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
    public List<MyMQuestionCommentVO> searchMyMQuestionCommentByPage(@Param("userId") Long userId, @Param("start") Long start, @Param("end") Long end);
    public int updateQuestionAnswerCommentNum(@Param("commentNum") Long commentNum, @Param("id") Long id);

    public int takeQuestionComment(@Param("isBest") Long isBest, @Param("id") Long id);
}

