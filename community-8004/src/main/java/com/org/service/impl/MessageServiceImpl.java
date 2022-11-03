package com.org.service.impl;

import com.org.model.Message;
import com.org.mapper.MessageMapper;
import com.org.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
