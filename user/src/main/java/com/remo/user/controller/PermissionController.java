package com.remo.user.controller;

import com.remo.user.pojo.vo.ResponseVo;
import com.remo.user.service.PermissionService;
import com.remo.user.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public ResponseVo listPermissions() {
        return ResponseUtil.initSuccessResultVO(permissionService.listUserPermissions());
    }

}
