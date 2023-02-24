package com.org.service;

import com.org.model.Sensitive;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author B.M
 * @since 2022-11-20
 */
public interface ISensitiveService extends IService<Sensitive> {

    public List<Sensitive> shSensitive(String word);

    public List<String> shWord();
}
