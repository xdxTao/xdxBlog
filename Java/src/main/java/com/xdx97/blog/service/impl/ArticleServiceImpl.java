package com.xdx97.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.ArticleDTO;
import com.xdx97.blog.bean.entity.ArticleDetail;
import com.xdx97.blog.bean.entity.ArticleMain;
import com.xdx97.blog.bean.query.ArticleQuery;
import com.xdx97.blog.bean.vo.ArticleListVO;
import com.xdx97.blog.bean.vo.ArticleVO;
import com.xdx97.blog.mapper.ArticleDetailMapper;
import com.xdx97.blog.mapper.ArticleMainMapper;
import com.xdx97.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticleServiceImpl  extends ServiceImpl<ArticleMainMapper, ArticleMain> implements ArticleService {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    @Override
    public ResultObj add(ArticleDTO article) {

        ArticleMain articleMain = ArticleMain.builder().build();
        BeanUtil.copyProperties(article, articleMain,"true");

        ArticleDetail articleDetail = ArticleDetail.builder().build();
        BeanUtil.copyProperties(article, articleDetail,"true");

        // todo 获取文章前200字符
        articleMain.setArticleDesc("ddddd")
                .setCreateBy(1)
                .setCreateAt(LocalDateTime.now());

        this.baseMapper.insert(articleMain);

        articleDetail.setCreateBy(1)
                .setCreateAt(LocalDateTime.now());
        articleDetail.setArticleId(articleMain.getId());
        articleDetailMapper.insert(articleDetail);


        return ResultObj.success();
    }

    @Override
    public ResultObj modify(ArticleDTO articleDTO) {

        ArticleMain articleMain = ArticleMain.builder().build();
        BeanUtil.copyProperties(articleDTO, articleMain,"true");


        // todo 获取文章前200字符
        articleMain.setArticleDesc("dddddd")
                .setUpdateBy(1)
                .setUpdateAt(LocalDateTime.now());
        this.baseMapper.updateById(articleMain);


        ArticleDetail articleDetail = articleDetailMapper.getByArticleId(articleMain.getId());

        articleDetail.setHtmlContext(articleDTO.getHtmlContext())
                .setMarkdownContext(articleDTO.getMarkdownContext())
                .setUpdateAt(LocalDateTime.now())
                .setUpdateBy(1);
        articleDetailMapper.updateById(articleDetail);


        return ResultObj.success();
    }

    @Override
    public ResultObj<ArticleVO> get(Integer id) {

        ArticleMain articleMain = lambdaQuery().eq(ArticleMain::getId, id).one();
        ArticleVO articleVO = ArticleVO.builder().build();
        BeanUtil.copyProperties(articleMain,articleVO, true);

        ArticleDetail articleDetail = articleDetailMapper.getByArticleId(articleMain.getId());
        articleVO.setMarkdownContext(articleDetail.getMarkdownContext());

        return ResultObj.success(articleVO);
    }

    @Override
    public ResultObj list(ArticleQuery articleQuery) {

        IPage<ArticleListVO> pageList = this.baseMapper.list(articleQuery);
        return ResultObj.success(pageList);
    }
}
