package com.org.service.impl;

import com.org.model.LearnProgress;
import com.org.mapper.LearnProgressMapper;
import com.org.service.ILearnProgressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学习进度表	 服务实现类
 * </p>
 *
 * @author B.M
 * @since 2022-11-05
 */
@Service
public class LearnProgressServiceImpl extends ServiceImpl<LearnProgressMapper, LearnProgress> implements ILearnProgressService {

}
