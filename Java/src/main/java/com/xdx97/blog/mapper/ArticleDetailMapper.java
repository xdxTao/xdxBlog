package com.xdx97.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdx97.blog.bean.entity.ArticleDetail;
import com.xdx97.blog.bean.entity.ArticleMain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 菜单mapper
 *
 * @author 小道仙97
 * @date 2022/03/19
 */
@Mapper
public interface ArticleDetailMapper extends BaseMapper<ArticleDetail> {
    /**
     * 通过文章id 获取文章详细
     * @param articleId
     * @return
     */
    ArticleDetail getByArticleId(@Param("articleId") Integer articleId);
}
