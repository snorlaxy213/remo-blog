package com.remo.userShiro.common.function;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
