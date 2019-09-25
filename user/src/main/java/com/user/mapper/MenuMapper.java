package com.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.user.pojo.po.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 菜单表 Mapper 接口
 *
 */
@Component
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据条件查询菜单
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    Page<Map<String, Object>> selectMenus(@Param("page") Page page, @Param("condition") String condition, @Param("level") String level, @Param("menuId") Long menuId, @Param("code") String code);

    /**
     * 根据条件查询菜单
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Long> getMenuIdsByRoleId(@Param("roleId") Long roleId);

    /**
     * 删除menu关联的relation
     *
     * @param menuId
     * @return
     * @date 2017年2月19日 下午4:10:59
     */
    int deleteRelationByMenu(@Param("menuId") Long menuId);

    /**
     * 获取资源url通过角色id
     *
     * @param roleId
     * @return
     * @date 2017年2月19日 下午7:12:38
     */
    List<String> getRestUrlsByRoleId(@Param("roleId") Long roleId);

    /**
     * 查询菜单树形列表
     *
     * @author fengshuonan
     * @Date 2019/2/23 22:03
     */
    List<Map<String, Object>> selectMenuTree(@Param("condition") String condition, @Param("level") String level);

    /**
     * 获取pcodes like某个code的菜单列表
     *
     * @author fengshuonan
     * @Date 2019/3/31 15:51
     */
    List<Menu> getMenusLikePcodes(@Param("code") String code);

}
