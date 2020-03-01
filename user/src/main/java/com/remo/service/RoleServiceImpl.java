package com.remo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.remo.mapper.RoleMapper;
import com.remo.pojo.dto.RoleDto;
import com.remo.pojo.po.Role;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service(value = "roleServiceImpl")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource(name = "dozerBeanMapper")
    private DozerBeanMapper mapper;

    @Override
    public List<RoleDto> listUserRoles() {
        log.info("<=============== listUserRoles() ===============>");
        List<Role> roles = this.list();
        List<RoleDto> roleDtos = Lists.newArrayList();
        roles.forEach(role -> roleDtos.add(mapper.map(role, RoleDto.class)));
        return roleDtos;
    }

    @Override
    public List<RoleDto> listUserRoles(String username) {
        log.info("<=============== listUserRoles(String username) ===============>");
        List<Role> roles = this.getBaseMapper().listUserRoles(username);
        List<RoleDto> roleDtos = Lists.newArrayList();
        roles.forEach(role -> roleDtos.add(mapper.map(role, RoleDto.class)));
        return roleDtos;
    }


}
