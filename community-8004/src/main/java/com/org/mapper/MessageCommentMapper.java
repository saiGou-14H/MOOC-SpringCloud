package com.org.mapper;

import com.org.model.MessageComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 资讯评论表	 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-11-03
 */
@Mapper
public interface MessageCommentMapper extends BaseMapper<MessageComment> {

    public List<MessageComment> shMsgCom(Long msg_id);
}
