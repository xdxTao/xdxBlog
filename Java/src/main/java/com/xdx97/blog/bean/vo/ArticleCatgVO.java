package com.xdx97.blog.bean.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.xdx97.blog.common.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ArticleCatgVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 文章分类名称
     */
    private String name;

    /**
     * 启用状态
     */
    private StatusEnum status;

    /**
     * 文章数量
     */
    private Integer articleCount;

    /**
     * 排序
     */
    private Integer sort;

    private List<ArticleCatgVO> children;
}
