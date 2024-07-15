package com.zhl.ialcohol.vo.response;

import com.zhl.ialcohol.entity.Menu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Description:
 * Author: ZhangHL
 * Version: 1.0
 * Create Date Time: 2024/7/15 9:27.
 */
@Data
public class MenuVO{
    private Integer id;
    private String name;
    private String link;
    private List<Menu> childrenList;
}
