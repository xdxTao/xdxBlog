package com.xdx97.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.entity.User;
import com.xdx97.blog.bean.query.MenuQuery;
import com.xdx97.blog.bean.query.UserQuery;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.bean.vo.MenuVO;
import com.xdx97.blog.bean.vo.UserVO;
import com.xdx97.blog.common.enums.StatusEnum;
import com.xdx97.blog.mapper.MenuMapper;
import com.xdx97.blog.mapper.RoleMenuRelationMapper;
import com.xdx97.blog.mapper.UserMapper;
import com.xdx97.blog.service.MenuService;
import com.xdx97.blog.service.RoleService;
import com.xdx97.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleMenuRelationMapper roleMenuRelationMapper;

    @Override
    public ResultObj information() {

        // TODO 获取当前用户的id
        int curId = 1;
        InformationVO informationVO = this.baseMapper.getInformationById(curId);

        List<MenuVO> list = roleMenuRelationMapper.getRoleMenuByRoleId(informationVO.getRoleId());
        List<MenuVO> tmpList = new ArrayList<>();
        for (MenuVO item : list) {
            List<MenuVO> collect = list.stream()
                    .filter(iten -> item.getId().equals(iten.getParentId()))
                    .sorted((a, b) -> a.getSort().compareTo(b.getSort()))
                    .collect(Collectors.toList());
            item.setChildren(collect);
            tmpList.addAll(collect);
        }
        list.removeAll(tmpList);

        informationVO.setMenus(list);
        return ResultObj.success(informationVO);
    }

    @Override
    public ResultObj add(User user) {
        user.setCreateAt(LocalDateTime.now())
            .setCreateBy(1)
            .setSalt("dasd")
            .setPassword("dasda")
            .setStatus(StatusEnum.ENABLE);

        this.baseMapper.insert(user);

        return ResultObj.success();
    }

    @Override
    public ResultObj lists(UserQuery userQuery) {

        IPage<UserVO> userVOList = this.baseMapper.lists(userQuery);
        return ResultObj.success(userVOList);
    }

    @Override
    public ResultObj modify(User user) {

        this.baseMapper.updateById(user);
        return ResultObj.success();
    }
}
