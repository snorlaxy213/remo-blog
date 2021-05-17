package com.remo.article.controller;


import com.remo.article.common.util.ResponseUtil;
import com.remo.article.common.util.exception.exception.BusinessException;
import com.remo.article.common.util.exception.exception.ParameterException;
import com.remo.article.pojo.dto.ArticleDto;
import com.remo.article.pojo.dto.SimpleArticleDto;
import com.remo.article.pojo.vo.ResponseVo;
import com.remo.article.pojo.vo.query.ListArticleQuery;
import com.remo.article.service.IArticleService;
import com.remo.basic.annotation.RemoLog;
import com.remo.basic.group.Insert;
import com.remo.basic.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

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

    @RemoLog("articleRanks")
    @ApiOperation(value = "articleRanks")
    @GetMapping("articleRanks")
    public ResponseVo articleRanks(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        List<SimpleArticleDto> simpleArticleDtos = articleService.listSimpleArticles();
        return ResponseUtil.initSuccessResponse(simpleArticleDtos);
    }

}
