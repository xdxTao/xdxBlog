package com.xdx97.blog.controller;

import cn.hutool.core.lang.Assert;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.ArticleDTO;
import com.xdx97.blog.bean.dto.FastArticleDTO;
import com.xdx97.blog.bean.query.ArticleQuery;
import com.xdx97.blog.bean.vo.ArticleVO;
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
     * 新增
     */
    @PostMapping("/add")
    public ResultObj add(@RequestBody  ArticleDTO articleDTO) {
        return articleService.add(articleDTO);
    }

    /**
     * 更新
     */
    @PostMapping("/modify")
    public ResultObj modify(@RequestBody  ArticleDTO articleDTO) {
        Assert.notNull(articleDTO.getId() ,"缺少必要参数ID");
        return articleService.modify(articleDTO);
    }

    /**
     * 快速更新
     */
    @PostMapping("/fastModify")
    public ResultObj fastModify(@RequestBody @Valid FastArticleDTO fastArticleDTO) {
        return articleService.fastModify(fastArticleDTO);
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
