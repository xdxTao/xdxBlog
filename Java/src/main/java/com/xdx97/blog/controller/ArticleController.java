package com.xdx97.blog.controller;

import cn.hutool.core.lang.Assert;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.ArticleDTO;
import com.xdx97.blog.bean.dto.FastArticleDTO;
import com.xdx97.blog.bean.query.ArticleQuery;
import com.xdx97.blog.bean.vo.ArticleVO;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.common.annotation.SkipLoginAuth;
import com.xdx97.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 文章
 *
 * @author xdx97
 * @date 2202/06/26
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    /**
     * 前台列表
     */
    @GetMapping("/front/list")
    @SkipLoginAuth
    public ResultObj frontList(ArticleQuery articleQuery) {
        return articleService.frontList(articleQuery);
    }

    /**
     * 阅读排行榜
     */
    @GetMapping("/front/read-top")
    @SkipLoginAuth
    public ResultObj readTop() {
        return articleService.readTop();
    }


    /**
     * 前台详情
     */
    @GetMapping("/front/detail/{id}")
    @SkipLoginAuth
    public ResultObj frontDetail(@PathVariable Integer id) {
        return articleService.frontDetail(id);
    }


    /******************************************************管理后台*****************************************************/

    /**
     * 新增
     */
    @PostMapping("/add")
    public ResultObj add(@RequestBody  ArticleDTO articleDTO, InformationVO informationVO) {
        return articleService.add(articleDTO, informationVO);
    }

    /**
     * 更新
     */
    @PostMapping("/modify")
    public ResultObj modify(@RequestBody  ArticleDTO articleDTO, InformationVO informationVO) {
        Assert.notNull(articleDTO.getId() ,"缺少必要参数ID");
        return articleService.modify(articleDTO, informationVO);
    }

    /**
     * 快速更新
     */
    @PostMapping("/fastModify")
    public ResultObj fastModify(@RequestBody @Valid FastArticleDTO fastArticleDTO, InformationVO informationVO) {
        return articleService.fastModify(fastArticleDTO, informationVO);
    }

    /**
     * 获取信息
     */
    @GetMapping("/get/{id}")
    public ResultObj<ArticleVO> get(@PathVariable Integer id) {
        return articleService.get(id);
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    public ResultObj list(@RequestBody ArticleQuery articleQuery) {
        return articleService.list(articleQuery);
    }

}
