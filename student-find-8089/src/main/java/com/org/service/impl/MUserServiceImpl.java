package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MUser;
import com.org.mapper.MUserMapper;
import com.org.service.MUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户表
 * 服务实现类
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Component
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements MUserService {
    @Autowired
    private MUserMapper mUserMapper;
    @Override
    public boolean updateUser(MUser mUser) {
        return mUserMapper.updateUser(mUser);
    }
}

