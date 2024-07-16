package com.zhl.ialcohol.service;

import com.mybatisflex.core.service.IService;
import com.zhl.ialcohol.entity.Post;
import com.zhl.ialcohol.vo.response.CategoryPostVO;
import com.zhl.ialcohol.vo.response.LatestPostVO;

import java.util.List;

/**
 *  服务层。
 *
 * @author 张海龙
 * @since 2024-07-15
 */
public interface IPostService extends IService<Post> {

    List<LatestPostVO> getLatestPosts();

    CategoryPostVO getCategoryPosts(Integer pageNum, Integer pageSize, String categoryName);
}
