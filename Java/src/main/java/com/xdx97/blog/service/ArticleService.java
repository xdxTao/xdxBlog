package com.xdx97.blog.service;

import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.ArticleDTO;
import com.xdx97.blog.bean.dto.FastArticleDTO;
import com.xdx97.blog.bean.query.ArticleQuery;
import com.xdx97.blog.bean.vo.ArticleVO;

public interface ArticleService {

    ResultObj add(ArticleDTO articleDTO);

    ResultObj modify(ArticleDTO articleDTO);

    ResultObj<ArticleVO> get(Integer id);

    ResultObj list(ArticleQuery articleQuery);

    ResultObj fastModify(FastArticleDTO fastArticleDTO);
}
