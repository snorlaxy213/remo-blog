package com.remo.user.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDto implements Serializable {
    private static final long serialVersionUID = -3378970538289758172L;
    private Long roleId;
    private String name;
    private String description;
    private Integer sort;
    private Integer version;
}
