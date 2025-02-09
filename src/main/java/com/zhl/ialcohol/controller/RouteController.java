package com.zhl.ialcohol.controller;

import com.zhl.ialcohol.model.Constant;
import com.zhl.ialcohol.service.IPostService;
import com.zhl.ialcohol.service.ITagService;
import com.zhl.ialcohol.vo.response.CategoryPostVO;
import com.zhl.ialcohol.vo.response.LatestPostVO;
import com.zhl.ialcohol.vo.response.PostInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * Author: ZhangHL
 * Version: 1.0
 * Create Date Time: 2024/7/11 21:45.
 */
@Controller
public class RouteController {
    private IPostService postService;
    @Resource(name = "postServiceImpl")
    public void setPostService(IPostService postService) {
        this.postService = postService;
    }
    private ITagService tagService;
    @Resource(name = "tagServiceImpl")
    public void setTagService(ITagService tagService) {
        this.tagService = tagService;
    }

    /***
     * description: 路由到首页
     * @author: ZhangHL
     * @return:
    */
    @GetMapping(value = {"/", "/home", "/index"})
    public String routeToHome(Model model) {
        // 从内存中获取菜单
        model.addAttribute("menus", Constant.menuTree);
        // 获取首页文章列表
        List<LatestPostVO> latestPosts = postService.getLatestPosts();
        model.addAttribute("latestPosts", latestPosts);
        // 获取所有标签列表
        List<String> tags = tagService.getUndeletedTags();
        model.addAttribute("tags", tags);

        Integer pageNum = new Random().nextInt(5) + 1;
        return "index-" + pageNum;
    }

    /***
     * description: 路由到分类页面
     * @author: ZhangHL
     * @param categoryName
     * @return:
    */
    @GetMapping("/category/{categoryName}/{pageNum}")
    public String routeToCategory(@PathVariable String categoryName, @PathVariable Integer pageNum, Model model) {
        model.addAttribute("pageNum", pageNum);
        // 从内存中获取菜单
        model.addAttribute("menus", Constant.menuTree);
        // 按基酒分页查询,默认获取5条数据
        CategoryPostVO categoryPostInfo = postService.getCategoryPosts(pageNum, 5, categoryName);
        model.addAttribute("categoryPostInfo", categoryPostInfo);

        return "category";
    }

    /***
     * description: 路由到文章页面
     * @author: ZhangHL
     * @param postId
     * @return:
     */
    @GetMapping("/post/{postId}")
    public String routeToPost(@PathVariable Integer postId, Model model) {
        // 从内存中获取菜单
        model.addAttribute("menus", Constant.menuTree);
        // 获取所有标签列表
        List<String> tags = tagService.getUndeletedTags();
        model.addAttribute("tags", tags);
        // 获取文章详情
        PostInfoVO postInfoVO = postService.getPostInfoById(postId);
        model.addAttribute("postInfo", postInfoVO);

        Integer pageNum = (postId % 3) + 1;
        return "post-" + pageNum;
    }
}
