package com.org.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.org.entity.MMessageComment;
import com.org.entity.vo.MMessageCommentVO;

import java.util.List;

/**
 * <p>
 * 资讯评论表
 * 服务类
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
public interface MMessageCommentService extends IService<MMessageComment> {
    boolean addMMessageComment(MMessageComment mMessageComment);
    List<MMessageCommentVO> searchMMessageCommentBy(Integer messageId);
}

