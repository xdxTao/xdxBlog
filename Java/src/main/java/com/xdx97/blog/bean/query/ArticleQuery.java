package com.xdx97.blog.bean.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class ArticleQuery extends Page {

    private String name;
}
