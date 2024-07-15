package com.zhl.ialcohol.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zhl.ialcohol.entity.Menu;
import com.zhl.ialcohol.mapper.MenuMapper;
import com.zhl.ialcohol.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author 张海龙
 * @since 2024-07-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
