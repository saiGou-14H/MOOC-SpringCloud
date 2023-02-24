package com.org.service;

import com.org.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.org.model.dto.UserDTO1;
import com.org.model.dto.UserDTO2;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表
 服务类
 * </p>
 *
 * @author B.M
 * @since 2022-10-23
 */
public interface IUserService extends IService<User> {

    public List<User> shUser(User user);

    public boolean udMyself(User user);

    public List<UserDTO1> shUserByCla2(Long cla_id);

    public List<UserDTO2> shStuLearnedPro(Map<String, Long> map);
}
