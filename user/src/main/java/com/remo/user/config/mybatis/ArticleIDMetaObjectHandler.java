package com.remo.user.config.mybatis;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.remo.auth.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 字段自动填充
 */
@Slf4j
@Component
public class ArticleIDMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    HttpServletRequest request;

    /**
     * field Insert fill data automatically
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        String username = JwtTokenUtils.getUsernameByToken(JwtTokenUtils.getJwtFromRequest(request));
        this.strictInsertFill(metaObject, "createUser", String.class, username);
    }

    /**
     * field Update fill data automatically
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        String username = JwtTokenUtils.getUsernameByToken(JwtTokenUtils.getJwtFromRequest(request));
        this.strictInsertFill(metaObject, "updateUser", String.class, username);
    }
}
