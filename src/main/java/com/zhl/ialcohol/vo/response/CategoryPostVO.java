package com.zhl.ialcohol.vo.response;

import lombok.Data;

import java.util.List;

/**
 * Description:
 * Author: ZhangHL
 * Version: 1.0
 * Create Date Time: 2024/7/15 17:53.
 */
@Data
public class CategoryPostVO {
    private String categoryName;
    private Integer totalCout;
    private List<LatestPostVO> latestPostVOList;

}
