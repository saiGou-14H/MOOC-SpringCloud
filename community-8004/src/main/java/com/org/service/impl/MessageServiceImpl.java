package com.org.service.impl;

import com.org.mapper.MessageCommentMapper;
import com.org.model.Message;
import com.org.mapper.MessageMapper;
import com.org.model.MessageComment;
import com.org.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资讯表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-11-03
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Autowired
    MessageMapper messageMapper;
    @Autowired
    MessageCommentMapper messageCommentMapper;

    @Override
    public boolean udMessage(Message message) {
        return messageMapper.udMessage(message);
    }

    @Override
    public List<MessageComment> shMsgCom(Long msg_id) {
        return messageCommentMapper.shMsgCom(msg_id);
    }
}
