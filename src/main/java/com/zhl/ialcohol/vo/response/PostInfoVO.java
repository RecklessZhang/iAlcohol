package com.zhl.ialcohol.vo.response;

import com.zhl.ialcohol.model.Constant;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * Author: ZhangHL
 * Version: 1.0
 * Create Date Time: 2024/7/16 11:50.
 */
@Data
public class PostInfoVO {
    private Integer id;
    private String title;
    private String category;
    private String content;
    private LocalDateTime recordTime;
    private List<String> tagList = new ArrayList<>();
    private List<String> imageList = Collections.singletonList(Constant.globalConfig.getBaseUrl() + "/electronic-gallery/default/cover/default-" + (new Random().nextInt(11) + 1) + ".jpg");
    private PostInfoVO prePostInfo;
    private PostInfoVO nextPostInfo;

}
