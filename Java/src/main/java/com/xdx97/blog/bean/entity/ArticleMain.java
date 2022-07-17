package com.xdx97.blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xdx97.blog.common.enums.ArticleTypeEnum;
import com.xdx97.blog.common.enums.YesOrNoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 文章主表
 *
 * @author xdx97
 * @date 2022/06/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@TableName("xdx_article_main")
public class ArticleMain {

    /**
     * id
     */
    @TableId
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
     * 文章描述
     */
    private String articleDesc;

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
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

    /**
     * 更新人
     */
    private Integer updateBy;
}
