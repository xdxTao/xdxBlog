package com.xdx97.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xdx97.blog.bean.entity.ArticleMain;
import com.xdx97.blog.bean.entity.Menu;
import com.xdx97.blog.bean.query.ArticleQuery;
import com.xdx97.blog.bean.query.MenuQuery;
import com.xdx97.blog.bean.vo.ArticleFrontListVO;
import com.xdx97.blog.bean.vo.ArticleFrontVO;
import com.xdx97.blog.bean.vo.ArticleListVO;
import com.xdx97.blog.bean.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章mapper
 *
 * @author 小道仙97
 * @date 2022/03/19
 */
@Mapper
public interface ArticleMainMapper extends BaseMapper<ArticleMain> {

    /**
     * 前台-文章列表
     */
    IPage<ArticleFrontListVO> frontList(@Param("query") ArticleQuery articleQuery);

    /**
     * 前台-阅读排行榜
     */
    List<Map<String, Object>> readTop();

    /**
     * 前台文章详情
     * @param id
     * @return
     */
    ArticleFrontVO frontDetailById(@Param("id") Integer id);


    IPage<ArticleListVO> list(@Param("query") ArticleQuery articleQuery);


}
