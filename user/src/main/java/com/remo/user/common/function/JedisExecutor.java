package com.remo.user.common.function;

import com.remo.user.common.exception.exception.RedisConnectException;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R execute(T t) throws RedisConnectException;
}
