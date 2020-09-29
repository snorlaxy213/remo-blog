package com.remo.user.runner;


import com.remo.user.common.exception.exception.RedisConnectException;
import com.remo.user.common.service.CacheService;
import com.remo.user.manager.UserManager;
import com.remo.user.pojo.po.User;
import com.remo.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * 缓存初始化
 */
//@Component
public class CacheInitRunner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheInitRunner.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private UserManager userManager;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(ApplicationArguments args) {
        try {
            LOGGER.info("Redis连接中 ······");
            cacheService.testConnect();

            LOGGER.info("缓存初始化 ······");
            LOGGER.info("缓存用户数据 ······");
            List<User> list = this.userService.list();
            for (User user : list) {
                userManager.loadUserRedisCache(user.getUsername());
            }
        } catch (Exception e) {
            LOGGER.error("缓存初始化失败，{}", e.getMessage());
            LOGGER.error(" ____   __    _   _ ");
            LOGGER.error("| |_   / /\\  | | | |");
            LOGGER.error("|_|   /_/--\\ |_| |_|__");
            LOGGER.error("                        ");
            LOGGER.error("Remo启动失败              ");
            if (e instanceof RedisConnectException) {
                LOGGER.error("Redis连接异常，请检查Redis连接配置并确保Redis服务已启动");
            }
            // 关闭 Remo
            context.close();
        }
    }
}
