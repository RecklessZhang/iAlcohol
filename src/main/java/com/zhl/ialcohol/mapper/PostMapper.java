package com.zhl.ialcohol.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zhl.ialcohol.entity.Post;
import com.zhl.ialcohol.vo.response.LatestPostVO;

import java.util.List;

/**
 *  映射层。
 *
 * @author 张海龙
 * @since 2024-07-15
 */
public interface PostMapper extends BaseMapper<Post> {

    List<LatestPostVO> selectLatestPosts();
}
