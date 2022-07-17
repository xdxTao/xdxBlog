package com.xdx97.blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 博客明细实体
 *
 * @author 小道仙97
 * @date 2022/06/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@TableName("xdx_article_detail")
public class ArticleDetail {

    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 博客id
     */
    private Integer articleId;

    /**
     * 文章内容-html
     */
    private String htmlContext;

    /**
     * 文章内容-markdown
     */
    private String markdownContext;

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
