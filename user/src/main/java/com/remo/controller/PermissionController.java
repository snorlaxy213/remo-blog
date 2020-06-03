package com.remo.controller;

import com.remo.pojo.vo.ResponseVo;
import com.remo.service.PermissionService;
import com.remo.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/permission")
@Api(tags = "Permission")
public class PermissionController {

    @Resource(name = "permissionService")
    PermissionService permissionService;

    @ApiOperation(value = "listPermissions", notes = "listPermissions")
    @GetMapping("/permissions")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo listPermissions() {
        return ResponseUtil.initSuccessResultVO(permissionService.listUserPermissions());
    }

}
