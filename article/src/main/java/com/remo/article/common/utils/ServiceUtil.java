package com.remo.article.common.utils;

import com.remo.article.pojo.entity.Base;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * common service function
 *
 * @author Jules
 * @date 2019/12/11
 */
@Slf4j
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
