package com.remo.user.utils;

import com.remo.user.common.function.CacheSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class RemoUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoUtil.class);

    /**
     * 缓存查询模板
     *
     * @param cacheSelector    查询缓存的方法
     * @param databaseSelector 数据库查询方法
     * @return T
     */
    public static <T> T selectCacheByTemplate(CacheSelector<T> cacheSelector, Supplier<T> databaseSelector) {
        try {
            LOGGER.debug("query data from redis ······");
            // 先查 Redis缓存
            T t = cacheSelector.select();
            if (t == null) {
                // 没有记录再查询数据库
                return databaseSelector.get();
            } else {
                return t;
            }
        } catch (Exception e) {
            // 缓存查询出错，则去数据库查询
            LOGGER.error("redis error：", e);
            LOGGER.debug("query data from database ······");
            return databaseSelector.get();
        }
    }
}
