package com.zhl.ialcohol.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zhl.ialcohol.entity.File;
import com.zhl.ialcohol.mapper.FileMapper;
import com.zhl.ialcohol.service.IFileService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author 张海龙
 * @since 2024-07-15
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

}
