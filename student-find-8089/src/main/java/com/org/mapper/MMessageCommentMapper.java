package com.org.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.MMessageComment;
import com.org.entity.vo.MMessageCommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资讯评论表
 * Mapper 接口
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Mapper
public interface MMessageCommentMapper extends BaseMapper<MMessageComment> {
    public boolean addMMessageComment(MMessageComment mMessageComment);

    //    public List<MessagePageVo> searchMMessageByPage(Integer start, Integer end);
    public List<MMessageCommentVO> searchMMessageCommentBy(@Param("messageId") Integer messageId);

    public int updateMessageCommentNum(@Param("commentNum") Long commentNum, @Param("id") Long id);
}

