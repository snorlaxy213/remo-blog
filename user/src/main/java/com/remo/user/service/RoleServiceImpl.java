package com.remo.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.remo.user.mapper.RoleMapper;
import com.remo.user.pojo.dto.RoleDto;
import com.remo.user.pojo.po.Role;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "roleService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource(name = "dozerBeanMapper")
    private DozerBeanMapper mapper;

    @Override
    public List<RoleDto> listUserRoles() {
        LOGGER.info("<=============== listUserRoles() ===============>");
        List<Role> roles = this.list();
        List<RoleDto> roleDtos = Lists.newArrayList();
        roles.forEach(role -> roleDtos.add(mapper.map(role, RoleDto.class)));
        return roleDtos;
    }

    @Override
    public List<RoleDto> listUserRoles(String username) {
        LOGGER.info("<=============== listUserRoles(String username) ===============>");
        List<Role> roles = this.getBaseMapper().listUserRoles(username);
        List<RoleDto> roleDtos = Lists.newArrayList();
        roles.forEach(role -> roleDtos.add(mapper.map(role, RoleDto.class)));
        return roleDtos;
    }

    @Override
    public RoleDto findUserRoleById(Long id) {
        return mapper.map(this.getById(id), RoleDto.class);
    }

    @Override
    public Long addRole(RoleDto roleDto) {
        LOGGER.info("<=============== addRole ===============>");
        Role role = mapper.map(roleDto, Role.class);
        this.save(role);
        return role.getRoleId();
    }

    @Override
    public Long updateRole(RoleDto roleDto) {
        LOGGER.info("<=============== updateUser ===============>");
        Role role = mapper.map(roleDto, Role.class);
        this.saveOrUpdate(role);
        return role.getRoleId();
    }
}
