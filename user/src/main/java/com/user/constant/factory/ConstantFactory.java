package com.user.constant.factory;

import com.user.util.ToolUtil;
import com.user.constant.cache.Cache;
import com.user.constant.cache.CacheKey;
import com.user.mapper.RoleMapper;
import com.user.pojo.po.Role;
import com.user.util.SpringContextHolder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 常量的生产工厂
 *
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    private RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    public String getSingleRoleName(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role role = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(role) && ToolUtil.isNotEmpty(role.getName())) {
            return role.getName();
        }
        return "";
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
    public String getSingleRoleTip(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getDescription();
        }
        return "";
    }
}
