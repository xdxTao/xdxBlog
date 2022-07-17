package com.xdx97.blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xdx97.blog.common.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 博客分类实体
 *
 * @author 小道仙97
 * @date 2022/06/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("xdx_article_catg")
public class ArticleCatg {

    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 文章分类名称
     */
    @NotBlank(message = "文章分类名称不能为空")
    private String name;

    /**
     * 启用状态
     */
    private StatusEnum status;

    /**
     * 排序
     */
    private Integer sort;

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
