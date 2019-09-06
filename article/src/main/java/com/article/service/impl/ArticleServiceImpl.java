package com.article.service.impl;

import com.article.pojo.dto.ArticleDto;
import com.article.pojo.entity.Article;
import com.article.service.IArticleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.article.mapper.ArticleMapper;
import org.dozer.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vino
 * @since 2019-08-26
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    Mapper dozerMapper;

    @Override
    public List<ArticleDto> listArticles() {
        List<Article> articles = this.selectList(new EntityWrapper<>());
        List<ArticleDto> articleDtos = new ArrayList<>();
        articles.forEach(article -> articleDtos.add(dozerMapper.map(article,ArticleDto.class)));
        return articleDtos;
    }

    @Override
    public ArticleDto getArticle(Long id) {
        Article article = this.selectById(id);
        return dozerMapper.map(article, ArticleDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertArticle(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        this.initPo(article,true);
        boolean flag = this.insert(article);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateArticle(ArticleDto articleDto) {
        Article article = this.selectById(articleDto.getId());
        article.setArticleTitle(articleDto.getArticleTitle());
        article.setArticleContent(articleDto.getArticleContent());
        article.setArticleTags(articleDto.getArticleTags());
        article.setArticleType(articleDto.getArticleType());
        article.setArticleCategories(articleDto.getArticleCategories());
        article.setUpdateDate(articleDto.getUpdateDate());
        article.setArticleTabloid(articleDto.getArticleTabloid());
        article.setVersion(articleDto.getVersion());
        this.initPo(article,false);
        boolean flag = this.updateById(article);
        if(flag){
            System.out.println("Update successfully");
        }else{
            System.out.println("Update failed due to modified by others");
        }
        return flag;
    }

    private void initPo(Article article,boolean isNew) {
        if (isNew) {
            article.setCreateTime(new Date());
            article.setCreateUser("vino");
        } else {
            article.setUpdateTime(new Date());
            article.setUpdateUser("vino");
        }
    }
}
