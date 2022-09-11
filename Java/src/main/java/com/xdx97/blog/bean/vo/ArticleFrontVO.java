package com.xdx97.blog.bean.vo;

import com.xdx97.blog.common.enums.ArticleTypeEnum;
import com.xdx97.blog.common.enums.YesOrNoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 前台文章VO
 *
 * @author xdx97
 * @date 22/08/30
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleFrontVO {

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 分类name
     */
    private String catgName;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章标签
     */
    private String label;

    /**
     * 文章标签
     */
    private List<String> labels;

    /**
     * 文章类型(ORIGINAL 原创, REPRINT 转载), TRANSLATE 翻译)
     */
    private ArticleTypeEnum type;

    /**
     * 文章内容-markdown
     */
    private String markdownContext;
}
