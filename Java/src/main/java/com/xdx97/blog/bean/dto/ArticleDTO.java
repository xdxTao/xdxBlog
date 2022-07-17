package com.xdx97.blog.bean.dto;

import com.xdx97.blog.common.enums.ArticleTypeEnum;
import com.xdx97.blog.common.enums.YesOrNoEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 文章DTO
 */
@Data
public class ArticleDTO {

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 分类id
     */
    @NotNull(message = "分类不能为空")
    private Integer catgId;

    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空")
    private String title;

    /**
     * 文章标签
     */
    @NotBlank(message = "文章标签不能为空")
    private String label;

    /**
     * 文章类型(ORIGINAL 原创, REPRINT 转载), TRANSLATE 翻译)
     */
    @NotNull(message = "文章类型不能为空")
    private ArticleTypeEnum type;

    /**
     * 是否草稿（YES是 NO否）
     */
    @NotNull(message = "是否草稿不能为空")
    private YesOrNoEnum draft;

    /**
     * 是否置顶（YES是 NO否）
     */
    @NotNull(message = "是否置顶不能为空")
    private YesOrNoEnum top;

    /**
     * 是否公开（YES是 NO否）
     */
    @NotNull(message = "是否公开不能为空")
    private YesOrNoEnum open;

    /**
     * 文章内容-html
     */
    @NotNull(message = "文章内容-html不能为空")
    private String htmlContext;

    /**
     * 文章内容-markdown
     */
    @NotNull(message = "文章内容-markdown不能为空")
    private String markdownContext;
}
