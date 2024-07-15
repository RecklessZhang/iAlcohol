package com.zhl.ialcohol.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Description:
 * Author: ZhangHL
 * Version: 1.0
 * Create Date Time: 2024/7/8 15:34.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    private String recordName;
    private LocalDateTime recordTime;
    private String updateName;
    private LocalDateTime updateTime;
}
