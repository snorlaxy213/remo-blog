package com.remo.common.domain.cache;

/**
 * 缓存标识前缀集合,常用在ConstantFactory类中
 */
public interface CacheKey {

    /**
     * 角色名称(单个)
     */
    String SINGLE_ROLE_NAME = "single_role_name_";

    /**
     * 角色英文名称
     */
    String SINGLE_ROLE_TIP = "single_role_tip_";
}
