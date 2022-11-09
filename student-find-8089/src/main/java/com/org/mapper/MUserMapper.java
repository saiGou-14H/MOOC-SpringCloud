package com.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.MUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表
 * Mapper 接口
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Mapper
public interface MUserMapper extends BaseMapper<MUser> {
    public boolean updateUser(MUser mUser);
}

