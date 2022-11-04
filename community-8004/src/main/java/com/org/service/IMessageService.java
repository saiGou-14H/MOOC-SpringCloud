package com.org.service;

import com.org.model.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.org.model.MessageComment;

import java.util.List;

/**
 * <p>
 * 资讯表	 服务类
 * </p>
 *
 * @author B.M
 * @since 2022-11-03
 */
public interface IMessageService extends IService<Message> {

    public boolean udMessage(Message message);

    public List<MessageComment> shMsgCom(Long msg_id);
}
