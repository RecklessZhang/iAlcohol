package com.zhl.ialcohol.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.table.TableDef;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zhl.ialcohol.entity.*;
import com.zhl.ialcohol.entity.table.*;
import com.zhl.ialcohol.mapper.*;
import com.zhl.ialcohol.model.Constant;
import com.zhl.ialcohol.service.IPostService;
import com.zhl.ialcohol.util.BeanCopyUtil;
import com.zhl.ialcohol.vo.response.CategoryPostVO;
import com.zhl.ialcohol.vo.response.LatestPostVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  服务层实现。
 *
 * @author 张海龙
 * @since 2024-07-15
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {
    private PostMapper postMapper;
    @Resource
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }
    private PostFileMapper postFileMapper;
    @Resource
    public void setPostFileMapper(PostFileMapper postFileMapper) {
        this.postFileMapper = postFileMapper;
    }
    private FileMapper fileMapper;
    @Resource
    public void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }
    private MenuPostMapper menuPostMapper;
    @Resource
    public void setMenuPostMapper(MenuPostMapper menuPostMapper) {
        this.menuPostMapper = menuPostMapper;
    }
    private MenuMapper menuMapper;
    @Resource
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }


    /***
     * description: 获取首页文章列表
     * @author: ZhangHL
     * @return:
    */
    @Override
    public List<LatestPostVO> getLatestPosts() {

        List<LatestPostVO> latestPostList = postMapper.selectLatestPosts();
        for (LatestPostVO latestPostVO : latestPostList) {
            // 图片集
            QueryWrapper postFileWrapper = QueryWrapper.create()
                                                        .where(PostFileTableDef.POST_FILE.POST_ID.eq(latestPostVO.getId()));
            List<PostFile> postFileList = postFileMapper.selectListByQuery(postFileWrapper);
            if (postFileList.size() > 0) {
                QueryWrapper fileWrapper = QueryWrapper.create()
                                                        .where(FileTableDef.FILE.ID.in(
                                                           postFileList.stream().map(PostFile::getFileId).collect(Collectors.toList())
                                                        ));
                List<File> fileList = fileMapper.selectListByQuery(fileWrapper);
                latestPostVO.setImageList(fileList.stream().map(file -> Constant.globalConfig.getBaseUrl() + file.getUrl()).collect(Collectors.toList()));
            }

            // 文章分类
            QueryWrapper menuPostWrapper = QueryWrapper.create()
                                                        .where(MenuPostTableDef.MENU_POST.POST_ID.eq(latestPostVO.getId()));
            List<MenuPost> menuPostList = menuPostMapper.selectListByQuery(menuPostWrapper);
            if (menuPostList.size() > 0) {
                QueryWrapper menuWrapper = QueryWrapper.create()
                                                        .where(MenuTableDef.MENU.ID.in(
                                                                menuPostList.stream().map(MenuPost::getMenuId).collect(Collectors.toList())
                                                        ));
                List<Menu> menuList = menuMapper.selectListByQuery(menuWrapper);
                latestPostVO.setCategory(menuList.get(0).getName());
            }
        }

        return latestPostList;
    }

    /***
     * description: 按基酒分页查询,默认获取5条数据
     * @author: ZhangHL
     * @param pageNum
     * @param pageSize
     * @param categoryName
     * @return:
    */
    @Override
    public CategoryPostVO getCategoryPosts(Integer pageNum, Integer pageSize, String categoryName) {
        CategoryPostVO categoryPostVO = new CategoryPostVO();
        categoryPostVO.setCategoryName(categoryName);

        QueryWrapper menuWrapper = QueryWrapper.create()
                                                .where(MenuTableDef.MENU.NAME.eq(categoryName));
        Menu menu = menuMapper.selectOneByQuery(menuWrapper);
        if (menu != null) {
            QueryWrapper menuPostWrapper = QueryWrapper.create()
                                                        .where(MenuPostTableDef.MENU_POST.MENU_ID.eq(menu.getId()));
            List<MenuPost> menuPostList = menuPostMapper.selectListByQuery(menuPostWrapper);
            categoryPostVO.setTotalCout(menuPostList.size());

            if (menuPostList.size() > 0) {
                QueryWrapper postWrapper = QueryWrapper.create()
                                                        .where(PostTableDef.POST.ID.in(
                                                                menuPostList.stream().map(MenuPost::getPostId).collect(Collectors.toList())
                                                        ))
                                                        .orderBy(PostTableDef.POST.RECORD_TIME, false)
                                                        .limit((pageNum - 1) * pageSize, pageSize);
                List<Post> postList = postMapper.selectListByQuery(postWrapper);

                List<LatestPostVO> latestPostVOList = new ArrayList<>();
                for (Post post : postList) {
                    LatestPostVO latestPostVO = BeanCopyUtil.INSTANCE.postToLatestPostVO(post);
                    latestPostVO.setCategory(categoryName);

                    // 图片集
                    QueryWrapper postFileWrapper = QueryWrapper.create()
                                                                .where(PostFileTableDef.POST_FILE.POST_ID.eq(post.getId()));
                    List<PostFile> postFileList = postFileMapper.selectListByQuery(postFileWrapper);
                    if (postFileList.size() > 0) {
                        QueryWrapper fileWrapper = QueryWrapper.create()
                                                                .where(FileTableDef.FILE.ID.in(
                                                                        postFileList.stream().map(PostFile::getFileId).collect(Collectors.toList())
                                                                ));
                        List<File> fileList = fileMapper.selectListByQuery(fileWrapper);
                        latestPostVO.setImageList(fileList.stream().map(file -> Constant.globalConfig.getBaseUrl() + file.getUrl()).collect(Collectors.toList()));
                    }

                    latestPostVOList.add(latestPostVO);
                }
                categoryPostVO.setLatestPostVOList(latestPostVOList);
            }
        }


        return categoryPostVO;
    }
}
