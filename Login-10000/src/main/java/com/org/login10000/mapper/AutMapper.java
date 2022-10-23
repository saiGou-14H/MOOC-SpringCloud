package com.org.login10000.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.entity.Aut;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AutMapper extends BaseMapper<Aut> {
    Aut selectOne(String email);
}
