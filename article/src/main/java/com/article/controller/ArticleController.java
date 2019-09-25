package com.article.controller;


import com.article.exception.exception.BusinessException;
import com.article.exception.exception.ParamException;
import com.article.pojo.dto.ArticleDto;
import com.article.pojo.dto.SimpleArticleDto;
import com.article.pojo.vo.ResponseVo;
import com.article.pojo.vo.query.ListArticleQuery;
import com.article.service.IArticleService;
import com.article.utils.ResponseUtil;
import com.article.validation.group.Insert;
import com.article.validation.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vino
 * @since 2019-08-26
 */

@RestController(value = "/article")
@Api(tags = "Article")
public class ArticleController {

    @Autowired
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
    public ResponseVo getArticle(@PathVariable(value = "id")Long id) {
        ArticleDto articleDto = articleService.getArticle(id);
        return ResponseUtil.initSuccessResponse(articleDto);
    }

    @ApiOperation(value = "listArticles")
    @PostMapping("listArticles")
    public ResponseVo listArticles(@RequestBody ListArticleQuery query) throws ParamException, BusinessException {
        List<ArticleDto> articles = articleService.listArticles(query);
        return ResponseUtil.initSuccessResponse(articles);
    }

    @ApiOperation(value = "listSimpleArticles")
    @GetMapping("listSimpleArticles")
    public ResponseVo listSimpleArticles() {
        List<SimpleArticleDto> simpleArticleDtos = articleService.listSimpleArticles();
        return ResponseUtil.initSuccessResponse(simpleArticleDtos);
    }



}
