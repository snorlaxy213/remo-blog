package com.remo.controller;

import com.remo.pojo.vo.ResponseVo;
import com.remo.service.RoleService;
import com.remo.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "UserRole")
public class UserRoleController {

    @Resource(name = "roleServiceImpl")
    RoleService roleServiceImpl;

    @ApiOperation(value = "findAll", notes = "findAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping("/userRole/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo listUserRoles() {
        return ResponseUtil.initSuccessResultVO(roleServiceImpl.listUserRoles());
    }
}
