package com.remo.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remo.user.mapper.PermissionMapper;
import com.remo.user.pojo.dto.PermissionDto;
import com.remo.user.pojo.po.Permission;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("permissionService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource(name = "dozerBeanMapper")
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public List<PermissionDto> listUserPermissions() {
        log.info("<=============== findUserPermissions ===============>");
        List<Permission> permissions = this.list();
        List<PermissionDto> permissionDtos = new ArrayList<>();
        permissions.forEach(permission -> permissionDtos.add(dozerBeanMapper.map(permission, PermissionDto.class)));
        return permissionDtos;
    }

    @Override
    public List<PermissionDto> listUserPermissions(String username) {
        log.info("<=============== findUserPermissions ===============>");
        List<Permission> permissions = this.getBaseMapper().findUserPermissions(username);
        List<PermissionDto> permissionDtos = new ArrayList<>();
        permissions.forEach(permission -> permissionDtos.add(dozerBeanMapper.map(permission, PermissionDto.class)));
        return permissionDtos;
    }
}
