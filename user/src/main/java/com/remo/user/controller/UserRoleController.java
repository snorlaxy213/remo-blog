package com.remo.user.controller;

import com.remo.user.pojo.dto.RoleDto;
import com.remo.user.pojo.vo.ResponseVo;
import com.remo.user.service.RoleService;
import com.remo.user.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public ResponseVo listUserRoles() {
        return ResponseUtil.initSuccessResultVO(roleService.listUserRoles());
    }

    @ApiOperation(value = "find userRole by id", notes = "find userRole by id")
    @GetMapping("/userRole/{id}")
    public ResponseVo findById(@PathVariable("id") Long id) {
        return ResponseUtil.initSuccessResultVO(roleService.findUserRoleById(id));
    }

    @ApiOperation(value = "add userRole", notes = "add userRole")
    @PostMapping("/userRole")
    public ResponseVo addUserRole(@RequestBody RoleDto roleDto) {
        return ResponseUtil.initSuccessResultVO(roleService.addRole(roleDto));
    }

    @ApiOperation(value = "update userRole", notes = "update userRole")
    @PutMapping("/userRole")
    public ResponseVo updateUserRole(@RequestBody RoleDto roleDto) {
        return ResponseUtil.initSuccessResultVO(roleService.updateRole(roleDto));
    }

}
