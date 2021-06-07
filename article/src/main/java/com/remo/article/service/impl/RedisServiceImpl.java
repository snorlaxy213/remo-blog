package com.remo.article.service.impl;

import com.remo.article.dao.RedisDao;
import com.remo.article.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Vino
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    private static final String RANK_LIST_NAME = "rankList";
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisDao redisDao;

    @Override
    public void addCore(int id, int score) {
        this.redisDao.addScore(RANK_LIST_NAME, id, score);
    }

    @Override
    public Set getTop(int top) {
        return this.redisDao.getTop(RANK_LIST_NAME, top);
    }

    @Override
    public Set getTopWithScore(int top) {
        return this.redisDao.getTopWithScore(RANK_LIST_NAME, top);
    }

    @Override
    public Set getTopWithScore(int start, int limit) {
        return this.redisDao.getTopWithScore(RANK_LIST_NAME, start, limit);
    }
}
