package com.remo.config.mybatis;


import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 字段自动填充（自定义十位数字）
 */
@Component
public class ArricleIDMetaObjectHandler extends MetaObjectHandler {

    /**
     * field Insert fill data automatically
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //自动填充articleId（文章ID）
        Object articleId = getFieldValByName("articleId",metaObject);
        if (articleId == null) {
            setFieldValByName("articleId", getRandomCode(), metaObject);
        }
    }

    /**
     * field Update fill data automatically
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
