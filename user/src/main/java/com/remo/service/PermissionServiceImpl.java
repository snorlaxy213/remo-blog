package com.remo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remo.mapper.PermissionMapper;
import com.remo.pojo.dto.PermissionDto;
import com.remo.pojo.po.Permission;
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
    private DozerBeanMapper mapper;

    @Override
    public List<PermissionDto> findUserPermissions(String username) {
        log.info("<=============== findUserPermissions ===============>");
        List<Permission> permissions = this.baseMapper.findUserPermissions(username);
        List<PermissionDto> permissionDtos = new ArrayList<>();
        permissions.forEach(permission -> permissionDtos.add(mapper.map(permission, PermissionDto.class)));
        return permissionDtos;
    }
}
