package com.xdx97.blog.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 当前用户的信息
 *
 * @author xdx97
 * @date 22/07/03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class InformationVO {

    /**
     * 姓名
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 菜单权限
     */
    private List<MenuVO> menus;

}
