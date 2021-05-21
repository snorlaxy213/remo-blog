package com.remo.article.service.impl;

import com.remo.article.common.util.RedisUtil;
import com.remo.article.pojo.dto.SimpleArticleDto;
import com.remo.article.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void articleLikes(String key) {

    }

    @Override
    public List<SimpleArticleDto> articleRanks(LocalDate from, LocalDate to) {

        redisUtil.incr("")
        return null;
    }
}
