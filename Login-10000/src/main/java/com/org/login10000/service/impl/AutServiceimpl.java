package com.org.login10000.service.impl;

import com.org.entity.Aut;
import com.org.login10000.mapper.AutMapper;
import com.org.login10000.service.AutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutServiceimpl implements AutService {
    @Autowired
    AutMapper autMapper;

    @Override
    public Aut selectOne(String email) {
        return autMapper.selectOne(email);
    }
}
