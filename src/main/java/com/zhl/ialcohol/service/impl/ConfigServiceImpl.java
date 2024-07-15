package com.zhl.ialcohol.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zhl.ialcohol.entity.Config;
import com.zhl.ialcohol.mapper.ConfigMapper;
import com.zhl.ialcohol.service.IConfigService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author 张海龙
 * @since 2024-07-15
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
