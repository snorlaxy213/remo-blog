package com.remo.controller;

import com.remo.pojo.dto.RoleDto;
import com.remo.pojo.vo.ResponseVo;
import com.remo.service.RoleService;
import com.remo.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userRole")
@Api(tags = "UserRole")
public class UserRoleController {

    @Resource(name = "roleService")
    RoleService roleService;

    @ApiOperation(value = "listUserRoles", notes = "listUserRoles")
    @GetMapping("/userRoles")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo listUserRoles() {
        return ResponseUtil.initSuccessResultVO(roleService.listUserRoles());
    }

    @ApiOperation(value = "find userRole by id", notes = "find userRole by id")
    @GetMapping("/userRole/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo findById(@PathVariable("id") Long id) {
        return ResponseUtil.initSuccessResultVO(roleService.findUserRoleById(id));
    }

    @ApiOperation(value = "add userRole", notes = "add userRole")
    @PostMapping("/userRole")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo addUserRole(@RequestBody RoleDto roleDto) {
        return ResponseUtil.initSuccessResultVO(roleService.addRole(roleDto));
    }

    @ApiOperation(value = "update userRole", notes = "update userRole")
    @PutMapping("/userRole")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseVo updateUserRole(@RequestBody RoleDto roleDto) {
        return ResponseUtil.initSuccessResultVO(roleService.updateRole(roleDto));
    }

}
