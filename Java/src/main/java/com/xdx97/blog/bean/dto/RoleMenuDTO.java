package com.xdx97.blog.bean.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色菜单dto
 *
 * @author xdx97
 * @date 22/03/26
 */
@Data
public class RoleMenuDTO {

    /**
     * 角色id
     */
    @NotNull(message = "角色不能为空")
    private Integer roleId;

    /**
     * 菜单权限id
     */
    private List<Integer> menuIds;
}
