package com.remo.article.service;

import com.remo.article.pojo.dto.SimpleArticleDto;

import java.time.LocalDate;
import java.util.List;

public interface RedisService {

    void articleLikes(String key);

    List<SimpleArticleDto> articleRanks(LocalDate from, LocalDate to);
}
