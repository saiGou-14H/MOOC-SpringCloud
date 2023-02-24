package com.org.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MMessageComment;
import com.org.entity.vo.MMessageCommentVO;
import com.org.mapper.MMessageCommentMapper;
import com.org.service.MMessageCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资讯评论表
 * 服务实现类
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Service
public class MMessageCommentServiceImpl extends ServiceImpl<MMessageCommentMapper, MMessageComment> implements MMessageCommentService {

    @Autowired
    private MMessageCommentMapper mMessageCommentMapper;

    @Override
    public boolean addMMessageComment(MMessageComment mMessageComment) {
        return mMessageCommentMapper.addMMessageComment(mMessageComment);
    }

    @Override
    public List<MMessageCommentVO> searchMMessageCommentBy(Integer messageId) {
        return mMessageCommentMapper.searchMMessageCommentBy(messageId);
    }

    @Override
    public int updateMessageCommentNum(Long commentNum, Long id) {
        return mMessageCommentMapper.updateMessageCommentNum(commentNum, id);
    }
}

