package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MMessage;
import com.org.entity.vo.MessagePageVO;
import com.org.mapper.MMessageMapper;
import com.org.service.MMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资讯表
 * 服务实现类
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Service
public class MMessageServiceImpl extends ServiceImpl<MMessageMapper, MMessage> implements MMessageService {
    @Autowired
    private MMessageMapper mMessageMapper;

    @Override
    public boolean addMessage(MMessage mMessage) {
        return mMessageMapper.addMessage(mMessage);
    }

    public List<MessagePageVO> searchMMessageByPage(Integer start, Integer end) {
        return mMessageMapper.searchMMessageByPage(start, end);
    }
}

