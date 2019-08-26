package com.article.controller;


import com.article.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "login", notes = "check if userName and password are correct")
    @PostMapping("login")
    public void test() {

    }

}
