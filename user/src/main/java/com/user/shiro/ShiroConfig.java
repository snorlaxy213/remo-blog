package com.user.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiRoConfig
 *
 * @author Jules
 * @date 2019/6/26
 */
@Configuration
public class ShiroConfig {

    /**
     * handle filter resource file
     * focus:single ShiroFilterFactoryBean config is wrong, Need to inject SecurityManager when initializing ShiroFilterFactoryBean
     * useful filter:
     *  anon: 无需认证（登录）可以访问
     *  authc:需要认证才可以访问
     *  user:如果使用remember可以直接访问
     *  perms：该资源必须得到权限才可以访问
     *  roles：该资源必须得到角色权限才可以访问
     * @author Jules
     * @date 2019/6/26
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(manager);

        // 在 Shiro过滤器链上加入 JWTFilter
        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap();
        //swagger接口权限 开放
//        filterMap.put("/swagger-ui.html", "anon");
//        filterMap.put("/webjars/**", "anon");
//        filterMap.put("/v2/**", "anon");
//        filterMap.put("/swagger-resources/**", "anon");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterMap.put("/**", "jwt");


        //配置拦截Url
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //不配置此属性login请求会转发到login.jsp
        shiroFilterFactoryBean.setLoginUrl("/login");

        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }

    /**
     * User Get AuthorizationInfo and Get AuthenticationInfo
     *
     * @author Jules
     * @date 2019/6/26
     */
    @Bean("shiroRealm")
    public ShiroRealm getShiroRealm() {
        ShiroRealm realm = new ShiroRealm();
        return realm;
    }


}
