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
public class FastArticleDTO {

    /**
     * 文章id
     */
    @NotNull(message = "ID不能为空")
    private Integer id;

    /**
     * 是否置顶（YES是 NO否）
     */
    private YesOrNoEnum top;

    /**
     * 是否公开（YES是 NO否）
     */
    private YesOrNoEnum open;
}
