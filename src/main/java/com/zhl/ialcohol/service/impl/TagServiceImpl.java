package com.zhl.ialcohol.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zhl.ialcohol.entity.Tag;
import com.zhl.ialcohol.entity.table.TagTableDef;
import com.zhl.ialcohol.mapper.TagMapper;
import com.zhl.ialcohol.service.ITagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  服务层实现。
 *
 * @author 张海龙
 * @since 2024-07-15
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {
    private TagMapper tagMapper;
    @Resource
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    /***
     * description: 获取所有标签列表
     * @author: ZhangHL
     * @return:
    */
    @Override
    public List<String> getUndeletedTags() {
        QueryWrapper tagWrapper = QueryWrapper.create()
                                                .where(TagTableDef.TAG.IS_DELETE.eq("0"));
        List<Tag> tagList = tagMapper.selectListByQuery(tagWrapper);

        return tagList.stream().map(Tag::getName).collect(Collectors.toList());
    }
}
