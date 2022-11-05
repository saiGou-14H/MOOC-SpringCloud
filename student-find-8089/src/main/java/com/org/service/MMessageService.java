package com.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.entity.MMessage;
import com.org.entity.vo.MessagePageVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资讯表
 * 服务类
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Service
public interface MMessageService extends IService<MMessage> {


    boolean addMessage(MMessage mMessage);
    List<MessagePageVO> searchMMessageByPage(Integer start, Integer end);
}

