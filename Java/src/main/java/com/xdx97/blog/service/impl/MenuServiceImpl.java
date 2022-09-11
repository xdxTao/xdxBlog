package com.xdx97.blog.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.SortDTO;
import com.xdx97.blog.bean.entity.Menu;
import com.xdx97.blog.bean.query.MenuQuery;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.bean.vo.MenuVO;
import com.xdx97.blog.common.enums.StatusEnum;
import com.xdx97.blog.mapper.MenuMapper;
import com.xdx97.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单服务
 *
 * @author 小道仙97
 * @date 2022-03-19
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public ResultObj add(Menu menu, InformationVO informationVO) {
        Menu one = this.lambdaQuery().eq(Menu::getName, menu.getName()).one();
        Assert.isNull(one,"当前名称已存在");

        menu.setCreateAt(LocalDateTime.now())
                .setCreateBy(informationVO.getId());
        int insert = menuMapper.insert(menu);
        return ResultObj.success();
    }

    @Override
    public ResultObj lists(MenuQuery menuQuery) {
        return ResultObj.success(getChildMenu(menuMapper.listByParentId(0, menuQuery), menuQuery));
    }

    /**
     * 递归去获取子节点
     * @param list
     * @return
     */
    public List<MenuVO> getChildMenu(List<MenuVO> list, MenuQuery menuQuery) {
        if (list == null) {
            return new ArrayList<>();
        }
        for (MenuVO item : list) {
            List<MenuVO> tmpList = menuMapper.listByParentId(item.getId(), menuQuery);
            item.setChildren(tmpList);
            getChildMenu(item.getChildren(), menuQuery);
        }
        return list;
    }

    @Override
    public ResultObj modify(Menu menu, InformationVO informationVO) {
        menu.setUpdateAt(LocalDateTime.now())
                .setUpdateBy(informationVO.getId());
        this.menuMapper.updateById(menu);
        return ResultObj.success();
    }
}
