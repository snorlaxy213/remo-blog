package com.remo.user.pojo.dto;

import java.io.Serializable;

/**
 * 用户角色类
 * @author Grio Vino
 */
public class RoleDto implements Serializable {

    private static final long serialVersionUID = -3378970538289758172L;

    private Long roleId;

    private String name;

    private String description;

    private Integer sort;

    private Integer version;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
