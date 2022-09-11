package com.xdx97.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.RoleMenuDTO;
import com.xdx97.blog.bean.entity.Role;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.bean.vo.RoleVO;
import com.xdx97.blog.common.enums.StatusEnum;
import com.xdx97.blog.mapper.RoleMapper;
import com.xdx97.blog.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色服务类
 *
 * @author xdx97
 * @date 22/03/26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {



    @Override
    public ResultObj add(Role role, InformationVO informationVO) {
        role.setStatus(StatusEnum.ENABLE)
                .setCreateBy(informationVO.getId())
                .setCreateAt(LocalDateTime.now());
        this.baseMapper.insert(role);
        return ResultObj.success();
    }

    @Override
    @Transactional
    public ResultObj roleMenu(RoleMenuDTO roleMenu, InformationVO informationVO) {
        this.baseMapper.deleteRoleMenuByRoleId(roleMenu.getRoleId());
        if ( !CollectionUtils.isEmpty(roleMenu.getMenuIds())) {
            this.baseMapper.updateRoleMenuByRoleId(roleMenu.getRoleId(), roleMenu.getMenuIds());
        }
        return ResultObj.success();
    }

    @Override
    public ResultObj lists() {
        List<RoleVO> roles =  this.baseMapper.lists();
        return ResultObj.success(roles);
    }

    @Override
    public ResultObj<List<Integer>> menuListByRoleId(String roleId) {
        List<Integer> menuIds = this.baseMapper.menuListByRoleId(roleId);
        return ResultObj.success(menuIds);
    }
}
