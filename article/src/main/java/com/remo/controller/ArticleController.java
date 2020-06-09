package com.remo.controller;


import com.remo.annotation.RemoLog;
import com.remo.common.exception.exception.BusinessException;
import com.remo.common.exception.exception.ParamException;
import com.remo.common.utils.ResponseUtil;
import com.remo.common.validation.group.Insert;
import com.remo.common.validation.group.Update;
import com.remo.pojo.dto.ArticleDto;
import com.remo.pojo.dto.SimpleArticleDto;
import com.remo.pojo.vo.ResponseVo;
import com.remo.pojo.vo.query.ListArticleQuery;
import com.remo.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController(value = "/article")
@Api(tags = "Article")
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

    @ApiOperation(value = "listArticles")
    @PostMapping("listArticles")
    public ResponseVo listArticles(@RequestBody ListArticleQuery query) throws ParamException, BusinessException {
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
