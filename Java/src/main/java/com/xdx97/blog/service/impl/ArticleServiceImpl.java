package com.xdx97.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.ArticleDTO;
import com.xdx97.blog.bean.dto.FastArticleDTO;
import com.xdx97.blog.bean.entity.ArticleDetail;
import com.xdx97.blog.bean.entity.ArticleMain;
import com.xdx97.blog.bean.query.ArticleQuery;
import com.xdx97.blog.bean.vo.ArticleListVO;
import com.xdx97.blog.bean.vo.ArticleVO;
import com.xdx97.blog.common.enums.ArticleTypeEnum;
import com.xdx97.blog.common.enums.YesOrNoEnum;
import com.xdx97.blog.common.utils.CommonSqlUtils;
import com.xdx97.blog.mapper.ArticleDetailMapper;
import com.xdx97.blog.mapper.ArticleMainMapper;
import com.xdx97.blog.mapper.CommonSqlMapper;
import com.xdx97.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class ArticleServiceImpl  extends ServiceImpl<ArticleMainMapper, ArticleMain> implements ArticleService {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    @Autowired
    private CommonSqlUtils commonSqlUtils;

    @Override
    public ResultObj add(ArticleDTO article) {

        ArticleMain articleMain = ArticleMain.builder().build();
        BeanUtil.copyProperties(article, articleMain,"true");

        ArticleDetail articleDetail = ArticleDetail.builder().build();
        BeanUtil.copyProperties(article, articleDetail,"true");

        // todo 获取文章前200字符
        articleMain.setArticleDesc("ddddd")
                .setCreateBy(1)
                .setCreateAt(LocalDateTime.now())
                .setUpdateAt(LocalDateTime.now());

        this.baseMapper.insert(articleMain);

        articleDetail.setCreateBy(1)
                .setCreateAt(LocalDateTime.now());
        articleDetail.setArticleId(articleMain.getId());
        articleDetailMapper.insert(articleDetail);


        return ResultObj.success(articleMain.getId());
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
        articleVO.setCatgIds(commonSqlUtils.allParentId("xdx_article_catg", articleVO.getCatgId()));
        articleVO.setLabels(Arrays.asList(articleVO.getLabel().split(",")));

        return ResultObj.success(articleVO);
    }

    @Override
    public ResultObj list(ArticleQuery articleQuery) {

        IPage<ArticleListVO> pageList = this.baseMapper.list(articleQuery);
        pageList.getRecords().forEach(item -> {
            item.setDraftName(YesOrNoEnum.valueOf(item.getDraftName()).getName());
            item.setTypeName(ArticleTypeEnum.valueOf(item.getTypeName()).getName());
        });
        return ResultObj.success(pageList);
    }

    @Override
    public ResultObj fastModify(FastArticleDTO dto) {

        this.lambdaUpdate()
                .set(dto.getOpen() != null,ArticleMain::getOpen,  dto.getOpen())
                .set(dto.getTop() != null, ArticleMain::getTop, dto.getTop())
                .eq(ArticleMain::getId, dto.getId())
                .update();

        return ResultObj.success();
    }
}
