package com.remo.user.controller;

import com.remo.user.pojo.dto.RoleDto;
import com.remo.user.pojo.vo.ResponseVo;
import com.remo.user.service.RoleService;
import com.remo.user.utils.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource(name = "roleService")
    RoleService roleService;

    @GetMapping("/userRoles")
    public ResponseVo listUserRoles() {
        return ResponseUtil.initSuccessResultVO(roleService.listUserRoles());
    }

    @GetMapping("/userRole/{id}")
    public ResponseVo findById(@PathVariable("id") Long id) {
        return ResponseUtil.initSuccessResultVO(roleService.findUserRoleById(id));
    }

    @PostMapping("/userRole")
    public ResponseVo addUserRole(@RequestBody RoleDto roleDto) {
        return ResponseUtil.initSuccessResultVO(roleService.addRole(roleDto));
    }

    @PutMapping("/userRole")
    public ResponseVo updateUserRole(@RequestBody RoleDto roleDto) {
        return ResponseUtil.initSuccessResultVO(roleService.updateRole(roleDto));
    }

}
