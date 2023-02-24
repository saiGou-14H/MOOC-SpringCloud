package com.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.entity.MUser;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表
 * 服务类
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@Service
public interface MUserService extends IService<MUser> {
    boolean updateUser(MUser mUser);
}

