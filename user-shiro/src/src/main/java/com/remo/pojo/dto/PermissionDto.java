package com.remo.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionDto implements Serializable {
    private static final long serialVersionUID = 7187628714679791771L;
    private Long permissionId;
    private String permissionName;
    private String path;
    private String type;
    private Long parentId;
    private Integer orderNum;
    private Integer version;
}