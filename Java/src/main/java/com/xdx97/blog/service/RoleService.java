package com.xdx97.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.RoleMenuDTO;
import com.xdx97.blog.bean.entity.Role;
import com.xdx97.blog.bean.vo.RoleVO;

import java.util.List;

/**
 * 角色接口
 *
 * @author 小道仙97
 * @date 2022/03/226
 */
public interface RoleService extends IService<Role> {

    /**
     * 添加角色
     * @param role
     * @return
     */
    ResultObj add(Role role);

    /**
     * 角色菜单权限变更
     * @param roleMenu
     * @return
     */
    ResultObj roleMenu(RoleMenuDTO roleMenu);

    /**
     * 角色列表
     * @return
     */
    ResultObj lists();

    /**
     * 通过roleid获取菜单id
     * @param roleId
     * @return
     */
    ResultObj<List<Integer>> menuListByRoleId(String roleId);
}
