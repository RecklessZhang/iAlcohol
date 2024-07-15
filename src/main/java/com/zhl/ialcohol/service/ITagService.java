package com.zhl.ialcohol.service;

import com.mybatisflex.core.service.IService;
import com.zhl.ialcohol.entity.Tag;

import java.util.List;

/**
 *  服务层。
 *
 * @author 张海龙
 * @since 2024-07-15
 */
public interface ITagService extends IService<Tag> {

    List<String> getUndeletedTags();
}
