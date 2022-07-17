package com.xdx97.blog.controller;

import cn.hutool.core.lang.Assert;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.RoleMenuDTO;
import com.xdx97.blog.bean.entity.Role;
import com.xdx97.blog.bean.vo.RoleVO;
import com.xdx97.blog.common.enums.StatusCodeEnum;
import com.xdx97.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色
 *
 * @author 小道仙97
 * @date 2022-03-26
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色
     */
    @PostMapping("/add")
    public ResultObj add(@Valid @RequestBody Role role) {

        return roleService.add(role);
    }

    /**
     * 添加菜单权限
     */
    @PostMapping("/role-menu")
    public ResultObj roleMenu(@Valid @RequestBody RoleMenuDTO roleMenu) {

        return roleService.roleMenu(roleMenu);
    }

    /**
     * 角色列表
     */
    @PostMapping("/list")
    public ResultObj<List<RoleVO>> lists() {

        return roleService.lists();
    }

    /**
     * 通过角色id获取菜单权限
     */
    @PostMapping("/menu-id-list/{roleId}")
    public ResultObj<List<Integer>> menuListByRoleId(@PathVariable String roleId) {
        Assert.notBlank(roleId, StatusCodeEnum.MISS_REQUIRED_PARAMS.getName() +":roleId");
        return roleService.menuListByRoleId(roleId);
    }

}
