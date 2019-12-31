package com.remo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remo.mapper.PermissionMapper;
import com.remo.pojo.dto.PermissionDto;
import com.remo.pojo.po.Permission;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("permissionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private Mapper dozer;

    @Override
    public List<PermissionDto> findUserPermissions(String username) {
        List<Permission> permissions = this.baseMapper.findUserPermissions(username);
        List<PermissionDto> permissionDtos = new ArrayList<>();
        permissions.forEach(permission -> permissionDtos.add(dozer.map(permission,PermissionDto.class)));
        return permissionDtos;
    }
}
