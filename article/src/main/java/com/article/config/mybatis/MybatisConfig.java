package com.article.config.mybatis;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Mybatis-plus 配置
 *
 * @author Jules
 * @date 2019/9/6
 */
@Configuration
public class MybatisConfig {

    @Resource(name = "remoRoutingDataSource")
    private DataSource remoRoutingDataSource;

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

    /**
     * 事务配置(1)
     *
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(remoRoutingDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 事务配置(2)
     *
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(remoRoutingDataSource);
    }
}
