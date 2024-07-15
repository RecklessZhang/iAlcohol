package com.zhl.ialcohol.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.table.TableDef;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zhl.ialcohol.entity.*;
import com.zhl.ialcohol.entity.table.FileTableDef;
import com.zhl.ialcohol.entity.table.MenuPostTableDef;
import com.zhl.ialcohol.entity.table.MenuTableDef;
import com.zhl.ialcohol.entity.table.PostFileTableDef;
import com.zhl.ialcohol.mapper.*;
import com.zhl.ialcohol.model.Constant;
import com.zhl.ialcohol.service.IPostService;
import com.zhl.ialcohol.vo.response.LatestPostVO;
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
}
