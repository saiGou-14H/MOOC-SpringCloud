package com.org.service.impl;

import com.org.model.MessageComment;
import com.org.mapper.MessageCommentMapper;
import com.org.service.IMessageCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资讯评论表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-11-03
 */
@Service
public class MessageCommentServiceImpl extends ServiceImpl<MessageCommentMapper, MessageComment> implements IMessageCommentService {

}
