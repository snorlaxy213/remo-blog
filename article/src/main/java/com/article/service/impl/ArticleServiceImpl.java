package com.article.service.impl;

import com.article.pojo.dto.ArticleDto;
import com.article.pojo.entity.Article;
import com.article.service.IArticleService;
import com.article.utils.constant.FormatConstant;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.article.mapper.ArticleMapper;
import org.apache.log4j.Logger;
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
 *  文章服务实现
 * </p>
 *
 * @author vino
 * @since 2019-08-26
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

//    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private static final Logger LOGGER = Logger.getLogger(ArticleServiceImpl.class);

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

        //文章标题
        article.setArticleTitle(articleDto.getArticleTitle());
        //文章内容
        article.setArticleContent(articleDto.getArticleContent());
        //文章标签
        article.setArticleTags(articleDto.getArticleTags());
        //文章类型
        article.setArticleType(articleDto.getArticleType());
        //文章分类
        article.setArticleCategories(articleDto.getArticleCategories());
        //更新文章日期
        article.setUpdateDate(FormatConstant.yyyyMMdd.format(new Date()));
        //文章摘要
        article.setArticleTabloid(articleDto.getArticleTabloid());
        //乐观锁
        article.setVersion(articleDto.getVersion());

        this.initPo(article,false);
        boolean flag = this.updateById(article);
        //记录日志
        if(flag){
            LOGGER.info("Update successfully");
        }else{
            LOGGER.info("Update failed due to modified by others");
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
