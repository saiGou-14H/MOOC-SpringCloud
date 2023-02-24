package com.org.service.impl;

import com.org.model.Sensitive;
import com.org.mapper.SensitiveMapper;
import com.org.service.ISensitiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-11-20
 */
@Service
public class SensitiveServiceImpl extends ServiceImpl<SensitiveMapper, Sensitive> implements ISensitiveService {

    @Autowired
    SensitiveMapper sensitiveMapper;

    @Override
    public List<Sensitive> shSensitive(String word) {
        return sensitiveMapper.shSensitive(word);
    }

    @Override
    public List<String> shWord() {
        return sensitiveMapper.shWord();
    }
}
