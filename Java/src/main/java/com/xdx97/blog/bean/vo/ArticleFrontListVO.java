package com.xdx97.blog.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xdx97.blog.common.enums.YesOrNoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 前台-文章列表VO
 *
 * @author xdx97
 * @date 22/08/15
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleFrontListVO {

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 文章描述
     */
    private String articleDesc;

    /**
     * 分类名称
     */
    private String catgName;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 点赞数
     */
    private Integer goodCount;

    /**
     * 阅读数
     */
    private Integer readCount;

    /**
     * 文章标签
     */
    private String label;

    /**
     * 文章类型(ORIGINAL 原创, REPRINT 转载), TRANSLATE 翻译)
     */
    private String typeName;

    /**
     * 是否置顶（YES是 NO否）
     */
    private YesOrNoEnum top;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;
}
