package com.zhl.ialcohol.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.zhl.ialcohol.entity.BaseEntity;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 *  实体类。
 *
 * @author 张海龙
 * @since 2024-07-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(value = "t_file")
public class File extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 文件路径
     */
    private String url;

    /**
     * 文件类型 图片细化到png,jpg等 视频细化到mp4,m3u8等
     */
    private String type;

    /**
     * 文件名
     */
    private String name;

    /**
     * 是否是封面 0否 1是
     */
    private String isCover;

    /**
     * 是否删除 0否 1是
     */
    @Column(isLogicDelete = true)
    private String isDelete;

}
