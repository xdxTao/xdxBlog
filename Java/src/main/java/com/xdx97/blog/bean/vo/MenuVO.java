package com.xdx97.blog.bean.vo;

import com.xdx97.blog.common.enums.StatusEnum;
import lombok.Data;

import java.util.List;

@Data
public class MenuVO {


    /**
     * id
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * icon
     */
    private String icon;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 父name
     */
    private String parentName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单url
     */
    private String menuUrl;

    /**
     * 启用状态
     */
    private StatusEnum status;

    /**
     * 子节点
     */
    private List<MenuVO> children;
}
