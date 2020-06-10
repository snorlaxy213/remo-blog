package com.remo.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * common service function
 *
 * @author Jules
 * @date 2019/12/11
 */
@Slf4j
public class ServiceUtil {

    public static <T> void initEntity(T entity, boolean isNew) {
        Class<?> entityClass = entity.getClass();

        String userId = "vino";
        Date now = new Date();
        try {
            if (isNew) {
                Method setCreateTime = entityClass.getMethod("setCreateTime", Date.class);
                Method setCreateUser = entityClass.getMethod("setCreateUser", String.class);
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, userId);
            }
            else {
                Method setUpdateTime = entityClass.getMethod("setUpdateTime", Date.class);
                Method setUpdateUser = entityClass.getMethod("setUpdateUser", String.class);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, userId);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("baseService initEntity error");
        }
    }
}
