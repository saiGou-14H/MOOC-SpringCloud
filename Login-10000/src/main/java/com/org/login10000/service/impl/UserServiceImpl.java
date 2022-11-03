package com.org.login10000.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MUser;
import com.org.login10000.mapper.UserMapper;
import com.org.login10000.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表
 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, MUser> implements IUserService {

}
