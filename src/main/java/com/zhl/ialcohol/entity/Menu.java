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
@Table(value = "t_menu")
public class Menu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Integer id;

    private String name;

    private String link;

    /**
     * 菜单层级 1第一级 2第二级 以此类推
     */
    private Integer grade;

    /**
     * 父菜单id 根节点的父id为0
     */
    private Integer parentId;

    /**
     * 是否删除 0否 1是
     */
    @Column(isLogicDelete = true)
    private String isDelete;

}
