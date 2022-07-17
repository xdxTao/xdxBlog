package com.xdx97.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.SortDTO;
import com.xdx97.blog.bean.entity.Menu;
import com.xdx97.blog.bean.query.MenuQuery;
import com.xdx97.blog.common.enums.StatusEnum;

import java.util.List;

/**
 * 菜单接口
 *
 * @author 小道仙97
 * @date 2022/03/19
 */
public interface MenuService extends IService<Menu> {

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    ResultObj add(Menu menu);

    /**
     * 获取菜单列表
     * @return
     */
    ResultObj lists(MenuQuery menuQuery);

    /**
     * 修改接口
     */
    ResultObj modify(Menu menu);
}
