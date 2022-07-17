package com.xdx97.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xdx97.blog.bean.entity.ArticleCatg;
import com.xdx97.blog.bean.query.ArticleCatgQuery;
import com.xdx97.blog.bean.vo.ArticleCatgVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章分类mapper
 *
 * @author 小道仙97
 * @date 2022/06/20
 */
@Mapper
public interface ArticleCatgMapper extends BaseMapper<ArticleCatg> {

    void testInsert(String name);

    IPage<ArticleCatgVO> listByParentId(@Param("parentId") Integer parentId, @Param("query") ArticleCatgQuery articleCatgQuery);

}
