package com.remo.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remo.user.mapper.PermissionMapper;
import com.remo.user.pojo.dto.PermissionDto;
import com.remo.user.pojo.po.Permission;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("permissionService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Resource(name = "dozerBeanMapper")
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public List<PermissionDto> listUserPermissions() {
        LOGGER.info("<=============== findUserPermissions ===============>");
        List<Permission> permissions = this.list();
        List<PermissionDto> permissionDtos = new ArrayList<>();
        permissions.forEach(permission -> permissionDtos.add(dozerBeanMapper.map(permission, PermissionDto.class)));
        return permissionDtos;
    }

    @Override
    public List<PermissionDto> listUserPermissions(String username) {
        LOGGER.info("<=============== findUserPermissions ===============>");
        List<Permission> permissions = this.getBaseMapper().findUserPermissions(username);
        List<PermissionDto> permissionDtos = new ArrayList<>();
        permissions.forEach(permission -> permissionDtos.add(dozerBeanMapper.map(permission, PermissionDto.class)));
        return permissionDtos;
    }
}
