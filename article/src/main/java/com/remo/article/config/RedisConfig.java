package com.remo.article.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig {

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    public GenericObjectPoolConfig redisPool() {
        return new GenericObjectPoolConfig();
    }

    /**
     * 配置第一个数据源-Master
     */
    @Bean("redisSentinelConfig")
    @Primary
    public RedisSentinelConfiguration redisSentinelConfig() {

        Map<String, Object> source = new HashMap<>(8);
        source.put("spring.redis.sentinel.nodes", environment.getProperty("spring.redis.sentinel.nodes"));
        RedisSentinelConfiguration redisSentinelConfiguration;
        redisSentinelConfiguration = new RedisSentinelConfiguration(new MapPropertySource("RedisSentinelConfiguration", source));
        redisSentinelConfiguration.setMaster(environment.getProperty("spring.redis.sentinel.master"));
        redisSentinelConfiguration.setPassword(environment.getProperty("spring.redis.password"));
        return redisSentinelConfiguration;
    }

    /**
     * 配置第一个数据源的连接工厂
     * 这里注意：需要添加@Primary 指定bean的名称，目的是为了创建两个不同名称的LettuceConnectionFactory
     *
     * @param redisPool
     * @param redisSentinelConfig
     */
    @Bean("lettuceConnectionFactory")
    @Primary
    public LettuceConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig redisPool, @Qualifier("redisSentinelConfig") RedisSentinelConfiguration redisSentinelConfig) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(redisPool).build();
        return new LettuceConnectionFactory(redisSentinelConfig, clientConfiguration);
    }

    /**
     * 配置第一个数据源的RedisTemplate
     * 注意：这里指定使用名称=factory 的 RedisConnectionFactory
     * 并且标识第一个数据源是默认数据源 @Primary
     *
     * @param redisConnectionFactory
     */
    @Bean("redisTemplate")
    @Primary
    public RedisTemplate redisTemplate(@Qualifier("lettuceConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return getRedisTemplate(redisConnectionFactory);
    }

    /**
     * 配置第二个数据源
     */
    @Bean("secondaryRedisStandaloneConfig")
    public RedisStandaloneConfiguration secondaryRedisConfig() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(environment.getProperty("spring.secondaryRedis.host"));
        redisStandaloneConfiguration.setPort(Integer.valueOf(environment.getProperty("spring.secondaryRedis.port")));
        redisStandaloneConfiguration.setPassword(environment.getProperty("spring.secondaryRedis.password"));

        return redisStandaloneConfiguration;
    }

    @Bean("secondaryLettuceConnectionFactory")
    public LettuceConnectionFactory secondaryLettuceConnectionFactory(GenericObjectPoolConfig redisPool, @Qualifier("secondaryRedisStandaloneConfig") RedisStandaloneConfiguration secondaryRedisStandaloneConfig) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(redisPool).build();
        return new LettuceConnectionFactory(secondaryRedisStandaloneConfig, clientConfiguration);
    }

    /**
     * 配置第一个数据源的RedisTemplate
     * 注意：这里指定使用名称=factory2 的 RedisConnectionFactory
     *
     * @param redisConnectionFactory
     */
    @Bean("secondaryRedisTemplate")
    public RedisTemplate secondaryRedisTemplate(@Qualifier("secondaryLettuceConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return getRedisTemplate(redisConnectionFactory);
    }

    private RedisTemplate getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // key采用String的序列化方式
        redisTemplate.setKeySerializer(keySerializer());
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(keySerializer());
        // value序列化方式采用jackson
        redisTemplate.setHashValueSerializer(valueSerializer());
        // hash的value序列化方式采用jackson
        redisTemplate.setValueSerializer(valueSerializer());

        return redisTemplate;
    }

    /**
     * String的序列化方式
     */
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    /**
     * jackson的序列化方式(默认使用JDK的序列化方式)
     */
    private RedisSerializer<Object> valueSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
//         om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }
}
