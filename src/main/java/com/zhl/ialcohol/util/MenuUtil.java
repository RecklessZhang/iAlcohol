package com.zhl.ialcohol.util;

import com.zhl.ialcohol.entity.Menu;
import com.zhl.ialcohol.vo.response.MenuVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: 菜单工具类
 * Author: ZhangHL
 * Version: 1.0
 * Create Date Time: 2024/7/15 9:52.
 */
public class MenuUtil {

    /***
     * description: 构建菜单树
     * @author: ZhangHL
     * @param menuList
     * @return:
    */
    public static List<MenuVO> buildMenuTree(List<Menu> menuList) {
        List<MenuVO> result = new ArrayList<>();

        // 第一级菜单
        List<Menu> fatherMenuList = menuList.stream()
                .filter(menu -> menu.getGrade() == 1)
                .collect(Collectors.toList());

        // 第二级菜单
        for (Menu fatherMenu : fatherMenuList) {
            List<Menu> childrenList = menuList.stream()
                    .filter(menu -> menu.getParentId().equals(fatherMenu.getId()))
                    .collect(Collectors.toList());

            MenuVO menuVO = BeanCopyUtil.INSTANCE.menuToMenuVO(fatherMenu);
            menuVO.setChildrenList(childrenList);
            result.add(menuVO);
        }

        return result;
    }

}
