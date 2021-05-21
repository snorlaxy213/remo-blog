package com.remo.article.service;

import java.util.Set;

public interface RedisService {

    void addCore(int id, int score);

    Set getTop(int top);

    Set getTopWithScore(int top);

    Set getTopWithScore(int start, int limit);
}
