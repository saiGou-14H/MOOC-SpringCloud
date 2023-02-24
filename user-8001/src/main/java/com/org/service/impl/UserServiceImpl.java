package com.org.service.impl;

import com.org.model.User;
import com.org.mapper.UserMapper;
import com.org.model.dto.UserDTO1;
import com.org.model.dto.UserDTO2;
import com.org.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> shUser(User user) {
        return userMapper.shUser(user);
    }

    @Override
    public boolean udMyself(User user) {
        return userMapper.udMyself(user);
    }

    @Override
    public List<UserDTO1> shUserByCla2(Long cla_id) {
        return userMapper.shUserByCla2(cla_id);
    }

    @Override
    public List<UserDTO2> shStuLearnedPro(Map<String, Long> map) {
        return userMapper.shStuLearnedPro(map);
    }
}
