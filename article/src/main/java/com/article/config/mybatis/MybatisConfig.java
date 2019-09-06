package com.article.config.mybatis;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * Mybatis-plus 配置
 *
 * @author Jules
 * @date 2019/9/6
 */
public class MybatisConfig {

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
