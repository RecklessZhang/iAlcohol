package com.zhl.ialcohol.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zhl.ialcohol.entity.MenuPost;
import com.zhl.ialcohol.mapper.MenuPostMapper;
import com.zhl.ialcohol.service.IMenuPostService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author 张海龙
 * @since 2024-07-15
 */
@Service
public class MenuPostServiceImpl extends ServiceImpl<MenuPostMapper, MenuPost> implements IMenuPostService {

}
