package com.org.mapper;

import com.org.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.model.dto.UserDTO1;
import com.org.model.dto.UserDTO2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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
public interface UserMapper extends BaseMapper<User> {

    public List<User> shUser(User user);

    public boolean udMyself(User user);

    public List<UserDTO1> shUserByCla2(Long cla_id);

    public List<UserDTO2> shStuLearnedPro(Map<String, Long> map);
}
