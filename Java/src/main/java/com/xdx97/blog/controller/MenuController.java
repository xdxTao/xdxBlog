package com.xdx97.blog.controller;

import cn.hutool.core.lang.Assert;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.entity.Menu;
import com.xdx97.blog.bean.query.MenuQuery;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.common.enums.StatusCodeEnum;
import com.xdx97.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 菜单
 *
 * @author 小道仙97
 * @date 2022-03-19
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 添加菜单
     */
    @PostMapping("/add")
    public ResultObj add(@Valid @RequestBody Menu menu, InformationVO informationVO) {

        return menuService.add(menu, informationVO);
    }

    /**
     * 菜单列表
     */
    @PostMapping("/list")
    public ResultObj list(@RequestBody MenuQuery menuQuery) {

        return menuService.lists(menuQuery);
    }

    /**
     * 改变状态
     */
    @PostMapping("/modify")
    public ResultObj modify(@RequestBody Menu menu, InformationVO informationVO) {
        Assert.notNull(menu.getId(), StatusCodeEnum.MISS_REQUIRED_PARAMS.getName() +":ID");
        return menuService.modify(menu, informationVO);
    }

}
