package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.pojo.po.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<Menu> findUserPermissions(String username);
}
