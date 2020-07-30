package com.remo.userShiro.common.function;

import com.remo.userShiro.common.exception.exception.RedisConnectException;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R execute(T t) throws RedisConnectException;
}
