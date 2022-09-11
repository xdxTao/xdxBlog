package com.xdx97.blog.service;

import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.ArticleDTO;
import com.xdx97.blog.bean.dto.FastArticleDTO;
import com.xdx97.blog.bean.query.ArticleQuery;
import com.xdx97.blog.bean.vo.ArticleVO;
import com.xdx97.blog.bean.vo.InformationVO;

public interface ArticleService {

    ResultObj frontList(ArticleQuery articleQuery);

    ResultObj readTop();

    ResultObj frontDetail(Integer id);

    ResultObj add(ArticleDTO articleDTO, InformationVO informationVO);

    ResultObj modify(ArticleDTO articleDTO, InformationVO informationVO);

    ResultObj<ArticleVO> get(Integer id);

    ResultObj list(ArticleQuery articleQuery);

    ResultObj fastModify(FastArticleDTO fastArticleDTO, InformationVO informationVO);


}
