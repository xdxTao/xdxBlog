package com.xdx97.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.ArticleDTO;
import com.xdx97.blog.bean.dto.FastArticleDTO;
import com.xdx97.blog.bean.entity.ArticleDetail;
import com.xdx97.blog.bean.entity.ArticleMain;
import com.xdx97.blog.bean.query.ArticleQuery;
import com.xdx97.blog.bean.vo.*;
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
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ArticleServiceImpl  extends ServiceImpl<ArticleMainMapper, ArticleMain> implements ArticleService {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    @Autowired
    private CommonSqlUtils commonSqlUtils;

    @Override
    public ResultObj frontList(ArticleQuery articleQuery) {

        IPage<ArticleFrontListVO> pageList = this.baseMapper.frontList(articleQuery);
        pageList.getRecords().forEach(item -> {
            item.setTypeName(ArticleTypeEnum.valueOf(item.getTypeName()).getName());
        });
        return ResultObj.success(pageList);
    }

    @Override
    public ResultObj readTop() {
        List<Map<String, Object>> list = this.baseMapper.readTop();
        return ResultObj.success(list);
    }

    @Override
    public ResultObj<ArticleFrontVO> frontDetail(Integer id) {

        ArticleFrontVO articleFrontVO = this.baseMapper.frontDetailById(id);
        Assert.notNull(articleFrontVO,"不存在的文章");

        articleFrontVO.setLabels(Arrays.asList(articleFrontVO.getLabel().split(",")));

        return ResultObj.success(articleFrontVO);
    }

    @Override
    public ResultObj add(ArticleDTO article, InformationVO informationVO) {

        ArticleMain articleMain = ArticleMain.builder().build();
        BeanUtil.copyProperties(article, articleMain,"true");

        ArticleDetail articleDetail = ArticleDetail.builder().build();
        BeanUtil.copyProperties(article, articleDetail,"true");

        articleMain.setArticleDesc(delHTMLTag(article.getHtmlContext()))
                .setCreateBy(informationVO.getId())
                .setCreateAt(LocalDateTime.now())
                .setUpdateAt(LocalDateTime.now());

        this.baseMapper.insert(articleMain);

        articleDetail.setCreateBy(informationVO.getId())
                .setCreateAt(LocalDateTime.now());
        articleDetail.setArticleId(articleMain.getId());
        articleDetailMapper.insert(articleDetail);


        return ResultObj.success(articleMain.getId());
    }

    @Override
    public ResultObj modify(ArticleDTO articleDTO, InformationVO informationVO) {

        ArticleMain articleMain = ArticleMain.builder().build();
        BeanUtil.copyProperties(articleDTO, articleMain,"true");


        articleMain.setArticleDesc(delHTMLTag(articleDTO.getHtmlContext()))
                .setUpdateBy(informationVO.getId())
                .setUpdateAt(LocalDateTime.now());
        this.baseMapper.updateById(articleMain);


        ArticleDetail articleDetail = articleDetailMapper.getByArticleId(articleMain.getId());

        articleDetail.setHtmlContext(articleDTO.getHtmlContext())
                .setMarkdownContext(articleDTO.getMarkdownContext())
                .setUpdateAt(LocalDateTime.now())
                .setUpdateBy(informationVO.getId());
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
    public ResultObj fastModify(FastArticleDTO dto, InformationVO informationVO) {

        this.lambdaUpdate()
                .set(dto.getOpen() != null,ArticleMain::getOpen,  dto.getOpen())
                .set(dto.getTop() != null, ArticleMain::getTop, dto.getTop())
                .set(ArticleMain::getUpdateBy, informationVO.getId())
                .set(ArticleMain::getUpdateAt, LocalDateTime.now())
                .eq(ArticleMain::getId, dto.getId())
                .update();

        return ResultObj.success();
    }

    // 定义script的正则表达式
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    // 定义style的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    // 定义HTML标签的正则表达式
    private static final String regEx_html = "<[^>]+>";
    // 定义空格回车换行符
    private static final String regEx_space = "\\s*|\t|\r|\n";
    //定义所有w标签
    private static final String regEx_w = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";

    /**
     * @param htmlStr
     * @return 删除Html标签
     * @author LongJin
     */
    public static String delHTMLTag(String htmlStr) {
        Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
        Matcher m_w = p_w.matcher(htmlStr);
        htmlStr = m_w.replaceAll(""); // 过滤script标签


        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签


        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签


        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签


        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签


        htmlStr = htmlStr.replaceAll(" ", "").trim(); //过滤
        if(htmlStr.length() > 150) {
            return htmlStr.substring(0, 150);
        }

        return htmlStr; // 返回文本字符串
    }
}
