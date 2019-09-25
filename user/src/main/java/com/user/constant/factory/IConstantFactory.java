package com.user.constant.factory;

/**
 * 常量生产工厂的接口
 *
 */
public interface IConstantFactory {

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Long roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Long roleId);
}
