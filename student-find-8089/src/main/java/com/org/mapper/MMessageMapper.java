package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.MMessage;
import com.org.entity.vo.MessagePageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资讯表
 * Mapper 接口
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Mapper
public interface MMessageMapper extends BaseMapper<MMessage> {
    public boolean addMessage(MMessage mMessage);
    public List<MessagePageVO> searchMMessageByPage(@Param("start")Integer start, @Param("end")Integer end);
}

