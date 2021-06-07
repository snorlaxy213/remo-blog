package com.remo.article.common.util;

import com.remo.article.pojo.entity.Base;

import java.time.LocalDateTime;

/**
 * common service function
 *
 * @author Vino
 * @date 2019/12/11
 */
public class ServiceUtil {

    public static <T extends Base> void initEntity(T entity, boolean isNew) {
        if (isNew) {
            entity.setCreateTime(LocalDateTime.now());
            entity.setCreateUser("VINO");
            entity.setIsDelete(0);
        } else {
            entity.setUpdateTime(LocalDateTime.now());
            entity.setUpdateUser("VINO");
        }
    }
}
