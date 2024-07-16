package com.zhl.ialcohol.util;

import com.zhl.ialcohol.entity.Menu;
import com.zhl.ialcohol.entity.Post;
import com.zhl.ialcohol.vo.response.LatestPostVO;
import com.zhl.ialcohol.vo.response.MenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Description: mapstruct实体类拷贝
 * Author: ZhangHL
 * Version: 1.0
 * Create Date Time: 2024/6/26 15:24.
 */
@Mapper // step1
public interface BeanCopyUtil {
    BeanCopyUtil INSTANCE = Mappers.getMapper(BeanCopyUtil.class);

    MenuVO menuToMenuVO(Menu menu);

    LatestPostVO postToLatestPostVO(Post post);
}
