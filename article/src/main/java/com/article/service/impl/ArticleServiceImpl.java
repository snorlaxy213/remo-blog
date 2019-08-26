package com.article.service.impl;

import com.article.entity.Article;
import com.article.service.IArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.article.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

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

}
