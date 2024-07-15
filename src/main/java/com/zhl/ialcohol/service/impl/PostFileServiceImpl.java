package com.zhl.ialcohol.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zhl.ialcohol.entity.PostFile;
import com.zhl.ialcohol.mapper.PostFileMapper;
import com.zhl.ialcohol.service.IPostFileService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author 张海龙
 * @since 2024-07-15
 */
@Service
public class PostFileServiceImpl extends ServiceImpl<PostFileMapper, PostFile> implements IPostFileService {

}
