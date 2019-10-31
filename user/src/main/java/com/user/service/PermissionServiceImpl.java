package com.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.mapper.PermissionMapper;
import com.user.pojo.po.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("menuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> findUserPermissions(String username) {
        return this.baseMapper.findUserPermissions(username);
    }
}
