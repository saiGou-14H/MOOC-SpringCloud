package com.org.login10000.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.MUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表
 Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@Mapper
public interface UserMapper extends BaseMapper<MUser> {

}
