package com.remo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.remo.mapper.UserMapper;
import com.remo.pojo.dto.UserDto;
import com.remo.pojo.po.User;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 不需要事务管理的(只查询的)方法：@Transactional(propagation=Propagation.NOT_SUPPORTED)
 * tips: @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)，只读事务，可以提高效率。
 * <p>
 * REQUIRED:业务方法需要在一个容器里运行。如果方法运行时，已经处在一个事务中，那么加入到这个事务，否则自己新建一个新的事务。
 * <p>
 * NOT_SUPPORTED:声明方法不需要事务。如果方法没有关联到一个事务，容器不会为他开启事务，如果方法在一个事务中被调用，该事务会被挂起，调用结束后，原先的事务会恢复执行。
 * <p>
 * REQUIRESNEW:不管是否存在事务，该方法总汇为自己发起一个新的事务。如果方法已经运行在一个事务中，则原有事务挂起，新的事务被创建。
 * <p>
 * MANDATORY：该方法只能在一个已经存在的事务中执行，业务方法不能发起自己的事务。如果在没有事务的环境下被调用，容器抛出例外。
 * <p>
 * SUPPORTS:该方法在某个事务范围内被调用，则方法成为该事务的一部分。如果方法在该事务范围外被调用，该方法就在没有事务的环境下执行。
 * <p>
 * NEVER：该方法绝对不能在事务范围内执行。如果在就抛例外。只有该方法没有关联到任何事务，才正常执行。
 * <p>
 * NESTED:如果一个活动的事务存在，则运行在一个嵌套的事务中。
 * 如果没有活动事务，则按REQUIRED属性执行。它使用了一个单独的事务，这个事务拥有多个可以回滚的保存点。内部事务的回滚不会对外部事务造成影响。
 * 它只对DataSourceTransactionManager事务管理器起效。
 */
@Slf4j
@Service(value = "userServiceImpl")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource(name = "dozerBeanMapper")
    private DozerBeanMapper mapper;

    @Override
    public List<UserDto> findAll() {
        log.info("<=============== findAll ===============>");
        List<User> users = this.list();
        List<UserDto> userDtos = Lists.newArrayList();
        users.forEach(user -> userDtos.add(mapper.map(user, UserDto.class)));
        return userDtos;
    }

    @Override
    public UserDto findById(Long id) {
        log.info("<=============== findById ===============>");
        return mapper.map(this.getById(id), UserDto.class);
    }

    @Override
    public UserDto findByUsername(String username) {
        log.info("<=============== findByUsername ===============>");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return mapper.map(this.getOne(wrapper.eq("username", username)), UserDto.class);
    }

    @Override
    public Long addUser(UserDto userDto) {
        log.info("<=============== addUser ===============>");
        User user = mapper.map(userDto, User.class);
        this.save(user);
        return user.getUserId();
    }

    @Override
    public Long updateUser(UserDto userDto) {
        log.info("<=============== updateUser ===============>");
        User user = mapper.map(userDto, User.class);
        this.saveOrUpdate(user);
        return user.getUserId();
    }
}
