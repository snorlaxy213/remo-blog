package com.article.config.mybatis;


import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ArricleIDMetaObjectHandler extends MetaObjectHandler {

    /**
     * field insert fill data automatically
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object articleId = getFieldValByName("articleId",metaObject);
        if (articleId == null) {
            setFieldValByName("articleId", getRandomCode(), metaObject);
        }
    }

    /**
     * field update fill data automatically
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        insertFill(metaObject);
    }

    /**
     * 获得10位随机码
     * @return 随机码
     */
    private Long getRandomCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            code.append(new Random().nextInt(10));
        }
        return Long.parseLong(code.toString());
    }
}
