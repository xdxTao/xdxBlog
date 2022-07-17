package com.xdx97.blog.service;

import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.entity.ArticleCatg;
import com.xdx97.blog.bean.query.ArticleCatgQuery;

public interface ArticleCatgService {

    ResultObj add(ArticleCatg articleCatg);

    ResultObj list(ArticleCatgQuery articleCatgQuery);

    ResultObj modify(ArticleCatg articleCatg);
}
