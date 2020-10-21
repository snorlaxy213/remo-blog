package com.remo.auth.config;

import com.remo.auth.exception.JWTAccessDeniedHandler;
import com.remo.auth.exception.JWTAuthenticationEntryPoint;
import com.remo.auth.filter.JWTAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                // 禁用 CSRF
                .csrf().disable()
                .authorizeRequests()
                //allow anonymous access to /user/login endpoint
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers("/swagger*//**").permitAll()
                .antMatchers("/v2/api-docs",//swagger api json
                        "/swagger-resources/configuration/ui",//用来获取支持的动作
                        "/swagger-resources",//用来获取api-docs的URL
                        "/swagger-resources/configuration/security",//安全选择
                        "/swagger-ui.html"
                ).permitAll()

                // authenticate all other requests
                .anyRequest().authenticated()
//                .antMatchers("/user/**").authenticated()
//                .antMatchers("/userRole/**").authenticated()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))//添加自定义Filter
                // 不需要session（不创建会话）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 授权异常处理
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                .accessDeniedHandler(new JWTAccessDeniedHandler());
    }
}
