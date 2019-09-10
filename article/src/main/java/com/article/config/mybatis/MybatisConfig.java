package com.article.config.mybatis;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-plus 配置
 *
 * @author Jules
 * @date 2019/9/6
 */
@Configuration
public class MybatisConfig {

    /**
     * 乐观锁实现配置：
     * 1 添加下列Bean
     * 2 注解(@Version)实体字段
     *
     * 乐观锁实现方式：
     * 取出记录时，获取当前version
     * 更新时，带上这个version
     * 执行更新时， set version = yourVersion+1 where version = yourVersion
     * 如果version不对，就更新失败
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
