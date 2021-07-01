package com.remo.article.controller;


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
@Api(tags = "Article")
@RestController(value = "/article")
@RequestMapping("/article")
public class ArticleController {

    @Resource
    @Qualifier("articleServiceImpl")
    private IArticleService articleService;

    @ApiOperation(value = "insertArticle")
    @PostMapping("insertArticle")
    public ResponseVo insertArticle(@Validated(Insert.class) @RequestBody ArticleDto articleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.initFailResponse(bindingResult);
        } else {
            boolean flag = articleService.insertArticle(articleDto);
            return ResponseUtil.initSuccessResponse(flag);
        }
    }

    @ApiOperation(value = "updateArticle")
    @PostMapping("updateArticle")
    public ResponseVo updateArticle(@Validated(Update.class) @RequestBody ArticleDto articleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.initFailResponse(bindingResult);
        } else {
            boolean flag = articleService.updateArticle(articleDto);
            return ResponseUtil.initSuccessResponse(flag);
        }
    }

    @ApiOperation(value = "getArticle")
    @GetMapping("getArticle/{id}")
    public ResponseVo getArticle(@PathVariable(value = "id") Long id) {
        ArticleDto articleDto = articleService.getArticle(id);
        return ResponseUtil.initSuccessResponse(articleDto);
    }

    @RemoLog("listArticles")
    @ApiOperation(value = "listArticles")
    @PostMapping("listArticles")
    public ResponseVo listArticles(@RequestBody ListArticleQuery query) throws ParameterException, BusinessException {
        List<ArticleDto> articles = articleService.listArticles(query);
        return ResponseUtil.initSuccessResponse(articles);
    }

    @RemoLog("listSimpleArticles")
    @ApiOperation(value = "listSimpleArticles")
    @GetMapping("listSimpleArticles")
    public ResponseVo listSimpleArticles() {
        List<SimpleArticleDto> simpleArticleDtos = articleService.listSimpleArticles();
        return ResponseUtil.initSuccessResponse(simpleArticleDtos);
    }

}
