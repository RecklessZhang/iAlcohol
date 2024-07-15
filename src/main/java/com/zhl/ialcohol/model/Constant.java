package com.zhl.ialcohol.model;

import com.mybatisflex.core.query.QueryWrapper;
import com.zhl.ialcohol.entity.Config;
import com.zhl.ialcohol.entity.Menu;
import com.zhl.ialcohol.entity.table.ConfigTableDef;
import com.zhl.ialcohol.service.IConfigService;
import com.zhl.ialcohol.service.IMenuService;
import com.zhl.ialcohol.util.MenuUtil;
import com.zhl.ialcohol.vo.response.MenuVO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Author: ZhangHL
 * Version: 1.0
 * Create Date Time: 2024/7/8 16:00.
 */
@Component
public class Constant {
    private IMenuService menuService;
    @Resource(name = "menuServiceImpl")
    public void setMenuService(IMenuService menuService) {
        this.menuService = menuService;
    }
    private IConfigService configService;
    @Resource(name = "configServiceImpl")
    public void setConfigService(IConfigService configService) {
        this.configService = configService;
    }

    public static List<MenuVO> menuTree;
    public static Config globalConfig;

    /***
     * description: 全局参数初始化
     * @author: ZhangHL
     * @return:
     */
    @PostConstruct
    public void initGlobalParams() {
        // 全局配置
        QueryWrapper selectWrapper = QueryWrapper.create()
                .where(ConfigTableDef.CONFIG.IS_DELETE.eq("0"));
        globalConfig = configService.getOne(selectWrapper);

        // 全局菜单
        List<Menu> menuList = menuService.list();
        if (menuList.size() > 0) {
            menuTree = MenuUtil.buildMenuTree(menuList);
        }
    }
}
