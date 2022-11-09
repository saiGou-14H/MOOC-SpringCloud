package com.org.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author Jie
 * @Date 2022-11-06
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
        setFieldValByName("payTime",LocalDateTime.now(),metaObject);
        setFieldValByName("date",LocalDateTime.now(),metaObject);
        setFieldValByName("addTime",LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}

