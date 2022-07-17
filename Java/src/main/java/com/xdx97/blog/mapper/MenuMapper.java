package com.xdx97.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdx97.blog.bean.entity.Menu;
import com.xdx97.blog.bean.query.MenuQuery;
import com.xdx97.blog.bean.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单mapper
 *
 * @author 小道仙97
 * @date 2022/03/19
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据父id获取列表信息
     * @param parentId
     * @param menuQuery
     * @return
     */
    List<MenuVO> listByParentId(@Param("parentId") Integer parentId,@Param("query") MenuQuery menuQuery);
}
