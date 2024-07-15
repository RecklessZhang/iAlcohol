package com.zhl.ialcohol.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhl.ialcohol.model.Constant;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Description:
 * Author: ZhangHL
 * Version: 1.0
 * Create Date Time: 2024/7/15 11:15.
 */
@Data
public class LatestPostVO {
    private Integer id;
    private String title;
    private String content;
    private List<String> imageList = Collections.singletonList(Constant.globalConfig.getBaseUrl() + "/electronic-gallery/default/cover/default-" + (new Random().nextInt(11) + 1) + ".jpg");
    private String category;
    private String isTop;
    private LocalDateTime recordTime;

}
