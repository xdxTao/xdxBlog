package com.xdx97.blog.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.entity.User;
import com.xdx97.blog.bean.query.MenuQuery;
import com.xdx97.blog.bean.query.UserQuery;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.bean.vo.MenuVO;
import com.xdx97.blog.bean.vo.UserVO;
import com.xdx97.blog.common.constant.SystemConstant;
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
    public ResultObj information(Integer id) {

        // TODO 获取当前用户的id
        int curId = id;
        InformationVO informationVO = this.baseMapper.getInformationById(curId);

        List<MenuVO> list = roleMenuRelationMapper.getRoleMenuByRoleId(informationVO.getRoleId());
        List<MenuVO> tmpList = new ArrayList<>();
        for (MenuVO item : list) {
            List<MenuVO> collect = list.stream()
                    .filter(iten -> item.getId().equals(iten.getParentId()))
                    .sorted((a, b) -> b.getSort().compareTo(a.getSort()))
                    .collect(Collectors.toList());
            item.setChildren(collect);
            tmpList.addAll(collect);
        }
        list.removeAll(tmpList);

        informationVO.setMenus(list);
        return ResultObj.success(informationVO);
    }

    @Override
    public ResultObj add(User user, InformationVO informationVO) {
        String salt = UUID.randomUUID().toString();
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        user.setCreateAt(LocalDateTime.now())
            .setCreateBy(informationVO.getId())
            .setSalt(salt)
            .setStatus(StatusEnum.ENABLE)
            .setPassword(md5.digestHex(user.getPassword() + salt));

        this.baseMapper.insert(user);
        return ResultObj.success();
    }

    @Override
    public ResultObj lists(UserQuery userQuery) {

        IPage<UserVO> userVOList = this.baseMapper.lists(userQuery);
        return ResultObj.success(userVOList);
    }

    @Override
    public ResultObj modify(User user, InformationVO informationVO) {
        String salt = UUID.randomUUID().toString();
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        user.setUpdateBy(informationVO.getId())
                .setUpdateAt(LocalDateTime.now())
                .setSalt(salt)
                .setStatus(StatusEnum.ENABLE)
                .setPassword(md5.digestHex(user.getPassword() + salt));
        this.baseMapper.updateById(user);
        return ResultObj.success();
    }
}
