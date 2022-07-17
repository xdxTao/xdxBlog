package com.xdx97.blog.bean.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdx97.blog.common.enums.StatusEnum;
import lombok.Data;

@Data
public class ArticleCatgQuery extends Page {

    private String name;

    /**
     * 启用状态
     */
    private StatusEnum status;
}
