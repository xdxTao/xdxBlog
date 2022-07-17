package com.xdx97.blog.bean.vo;

import com.xdx97.blog.common.enums.ArticleTypeEnum;
import com.xdx97.blog.common.enums.YesOrNoEnum;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 文章VO
 *
 * @author xdx97
 * @date 22/07/02
 */
@Data
@Accessors(chain = true)
@Builder
public class ArticleVO {

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 分类id
     */
    private Integer catgId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章标签
     */
    private String label;

    /**
     * 文章类型(ORIGINAL 原创, REPRINT 转载), TRANSLATE 翻译)
     */
    private ArticleTypeEnum type;

    /**
     * 是否草稿（YES是 NO否）
     */
    private YesOrNoEnum draft;

    /**
     * 是否置顶（YES是 NO否）
     */
    private YesOrNoEnum top;

    /**
     * 是否公开（YES是 NO否）
     */
    private YesOrNoEnum open;

    /**
     * 文章内容-html
     */
    private String htmlContext;

    /**
     * 文章内容-markdown
     */
    private String markdownContext;
}
