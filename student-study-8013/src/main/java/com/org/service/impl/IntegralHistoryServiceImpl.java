package com.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.entity.MIntegralHistory;
import com.org.mapper.IIntegralHistoryMapper;
import com.org.service.IIntegralHistoryService;
import org.springframework.stereotype.Service;

@Service
public class IntegralHistoryServiceImpl extends ServiceImpl<IIntegralHistoryMapper, MIntegralHistory> implements IIntegralHistoryService {
}
