package com.xdx97.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xdx97.blog.bean.entity.ArticleMain;
import com.xdx97.blog.bean.entity.Menu;
import com.xdx97.blog.bean.query.ArticleQuery;
import com.xdx97.blog.bean.query.MenuQuery;
import com.xdx97.blog.bean.vo.ArticleListVO;
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
public interface ArticleMainMapper extends BaseMapper<ArticleMain> {


    IPage<ArticleListVO> list(@Param("query") ArticleQuery articleQuery);
}
