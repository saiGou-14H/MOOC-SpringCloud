package com.org.mapper;

import com.org.model.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 资讯表	 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-11-03
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    public boolean udMessage(Message message);
}
