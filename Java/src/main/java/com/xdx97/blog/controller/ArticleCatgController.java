package com.xdx97.blog.controller;

import cn.hutool.core.lang.Assert;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.entity.ArticleCatg;
import com.xdx97.blog.bean.query.ArticleCatgQuery;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.common.annotation.SkipLoginAuth;
import com.xdx97.blog.common.enums.StatusCodeEnum;
import com.xdx97.blog.service.ArticleCatgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 文章分类
 *
 * @author 小道仙97
 * @date 2022-06-20
 */
@RestController
@RequestMapping("/article-catg")
public class ArticleCatgController {

    @Autowired
    private ArticleCatgService articleCatgService;


    /**
     * 前台-文章分类列表
     */
    @PostMapping("/front/list")
    public ResultObj frontList(@RequestBody ArticleCatgQuery articleCatgQuery) {

        return articleCatgService.frontList(articleCatgQuery);
    }

    /***********************************************************后端***************************************************************/

    /**
     * 添加文章分类
     */
    @PostMapping("/add")
    public ResultObj add(@Valid @RequestBody ArticleCatg articleCatg, InformationVO informationVO) {

        return articleCatgService.add(articleCatg, informationVO);
    }

    /**
     * 文章分类列表
     */
    @PostMapping("/list")
    public ResultObj list(@RequestBody ArticleCatgQuery articleCatgQuery) {

        return articleCatgService.list(articleCatgQuery);
    }

    /**
     * 分类更新
     */
    @PostMapping("/modify")
    public ResultObj modify(@RequestBody ArticleCatg articleCatg, InformationVO informationVO) {
        Assert.notNull(articleCatg.getId(), StatusCodeEnum.MISS_REQUIRED_PARAMS.getName() +":ID");
        return articleCatgService.modify(articleCatg, informationVO);
    }




}
