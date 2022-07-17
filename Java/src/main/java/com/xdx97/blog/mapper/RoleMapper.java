package com.xdx97.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdx97.blog.bean.entity.Role;
import com.xdx97.blog.bean.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色mapper
 *
 * @author xdx97
 * @date 22/03/26
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    void deleteRoleMenuByRoleId(@Param("roleId") Integer roleId);

    void updateRoleMenuByRoleId(@Param("roleId") Integer roleId,@Param("menuIds")  List<Integer> menuIds);

    List<RoleVO> lists();

    List<Integer> menuListByRoleId(@Param("roleId") String roleId);
}
