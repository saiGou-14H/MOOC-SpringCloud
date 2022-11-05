package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MUser;
import com.org.mapper.IUserMapper;
import com.org.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl extends ServiceImpl<IUserMapper, MUser> implements IUserService{
}
