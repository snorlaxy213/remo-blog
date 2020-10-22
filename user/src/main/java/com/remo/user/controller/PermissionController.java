package com.remo.user.controller;

import com.remo.user.pojo.vo.ResponseVo;
import com.remo.user.service.PermissionService;
import com.remo.user.utils.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource(name = "permissionService")
    PermissionService permissionService;

    @GetMapping("/permissions")
    public ResponseVo listPermissions() {
        return ResponseUtil.initSuccessResultVO(permissionService.listUserPermissions());
    }

}
