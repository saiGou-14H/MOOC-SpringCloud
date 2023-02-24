package com.org.mapper;

import com.org.model.Sensitive;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author B.M
 * @since 2022-11-20
 */
@Mapper
public interface SensitiveMapper extends BaseMapper<Sensitive> {

    public List<Sensitive> shSensitive(String word);

    public List<String> shWord();
}
