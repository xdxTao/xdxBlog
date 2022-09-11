package com.xdx97.blog.service;

import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.entity.ArticleCatg;
import com.xdx97.blog.bean.query.ArticleCatgQuery;
import com.xdx97.blog.bean.vo.InformationVO;

public interface ArticleCatgService {

    ResultObj frontList(ArticleCatgQuery articleCatgQuery);

    ResultObj add(ArticleCatg articleCatg, InformationVO informationVO);

    ResultObj list(ArticleCatgQuery articleCatgQuery);

    ResultObj modify(ArticleCatg articleCatg, InformationVO informationVO);
}
