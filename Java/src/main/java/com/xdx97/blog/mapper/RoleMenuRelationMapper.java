package com.xdx97.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdx97.blog.bean.entity.RoleMenuRelation;
import com.xdx97.blog.bean.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色菜单mapper
 *
 * @author xdx97
 * @date 22/03/26
 */
@Mapper
public interface RoleMenuRelationMapper extends BaseMapper<RoleMenuRelation> {

    List<MenuVO> getRoleMenuByRoleId(@Param("roleId") Integer roleId);

}
