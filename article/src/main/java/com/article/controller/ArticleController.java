package com.article.controller;


import com.article.pojo.dto.ArticleDto;
import com.article.pojo.vo.ResponseVo;
import com.article.service.IArticleService;
import com.article.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseVo insertArticle(@RequestBody ArticleDto articleDto) {
        boolean flag = articleService.insertArticle(articleDto);
        return ResponseUtil.initSuccessResponse(flag);
    }

    @ApiOperation(value = "updateArticle")
    @PostMapping("updateArticle")
    public ResponseVo updateArticle(@RequestBody ArticleDto articleDto) {
        boolean flag = articleService.updateArticle(articleDto);
        return ResponseUtil.initSuccessResponse(flag);
    }

    @ApiOperation(value = "getArticle")
    @GetMapping("getArticle/{id}")
    public ResponseVo getArticle(@PathVariable(value = "id")Long id) {
        ArticleDto articleDto = articleService.getArticle(id);
        return ResponseUtil.initSuccessResponse(articleDto);
    }

    @ApiOperation(value = "listArticle")
    @GetMapping("listArticle")
    public ResponseVo listArticle() {
        List<ArticleDto> articles = articleService.listArticles();
        return ResponseUtil.initSuccessResponse(articles);
    }

}
