package com.remo.article.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.remo.article.common.exception.exception.BusinessException;
import com.remo.article.common.exception.exception.ParameterException;
import com.remo.article.common.util.ResponseUtil;
import com.remo.article.pojo.dto.ArticleDto;
import com.remo.article.pojo.dto.SimpleArticleDto;
import com.remo.article.pojo.vo.ResponseVo;
import com.remo.article.pojo.vo.query.ListArticleQuery;
import com.remo.article.service.IArticleService;
import com.remo.basic.annotation.RemoLog;
import com.remo.basic.group.Insert;
import com.remo.basic.group.Update;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Vino
 */
@Api(tags = "Article", value = "文章服务管理")
@RestController(value = "/article")
@RequestMapping("/article")
public class ArticleController {

    @Resource
    @Qualifier("articleServiceImpl")
    private IArticleService articleService;

    /**
     * 新增文章
     * @param articleDto 文章数据
     * @param bindingResult JSR303校验参数
     * @return 新增成功/失败
     */
    @ApiOperation(value = "新增文章")
    @PostMapping("insertArticle")
    public ResponseVo insertArticle(@Validated(Insert.class) @RequestBody ArticleDto articleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.initFailResponse(bindingResult);
        } else {
            boolean flag = articleService.insertArticle(articleDto);
            return ResponseUtil.initSuccessResponse(flag);
        }
    }

    /**
     * 修改文章
     * @param articleDto 文章数据
     * @param bindingResult JSR303校验参数
     * @return 修改成功/失败
     */
    @ApiOperation(value = "修改文章")
    @PostMapping("updateArticle")
    public ResponseVo updateArticle(@Validated(Update.class) @RequestBody ArticleDto articleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.initFailResponse(bindingResult);
        } else {
            boolean flag = articleService.updateArticle(articleDto);
            return ResponseUtil.initSuccessResponse(flag);
        }
    }

    /**
     * 根据ID获取单篇文章
     * @param articleId 文章ID
     * @return 单篇文章
     */
    @ApiOperation(value = "根据ID获取单篇文章")
    @GetMapping("getArticle/{articleId}")
    public ResponseVo getArticle(@PathVariable(value = "articleId") Long articleId) {
        ArticleDto articleDto = articleService.getArticle(articleId);
        return ResponseUtil.initSuccessResponse(articleDto);
    }

    /**
     * 展示文章列表数据,支持分页
     * @param query 查询参数
     * @return 多篇文章
     * @throws ParameterException 参数校验异常
     * @throws BusinessException 业务校验异常
     */
    @ApiOperation(value = "展示文章列表数据")
    @PostMapping("listArticles")
    public ResponseVo listArticles(@RequestBody ListArticleQuery query) throws ParameterException, BusinessException {
        List<ArticleDto> articles = articleService.listArticles(query);
        return ResponseUtil.initSuccessResponse(articles);
    }

    /**
     * 展示多条文章及其相关简介
     * @return 文章及其相关简介
     */
    @RemoLog("listSimpleArticles")
    @ApiOperation(value = "listSimpleArticles")
    @GetMapping("listSimpleArticles")
    public ResponseVo listSimpleArticles() {
        List<SimpleArticleDto> simpleArticleDtos = articleService.listSimpleArticles();
        return ResponseUtil.initSuccessResponse(simpleArticleDtos);
    }

}
