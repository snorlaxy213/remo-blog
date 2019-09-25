package com.user.pojo.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单实体类
 *
 * @author Jules
 * @date 2019/7/9
 */
@Data
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 7204644055019433449L;

    /**
     * 主键id
     */
    @TableId(value = "menu_id", type = IdType.ID_WORKER)
    private Long menuId;

    /**
     * 菜单编号
     */
    @TableField("code")
    private String code;

    /**
     * 菜单父编号
     */
    @TableField("pcode")
    private String pcode;

    /**
     * 当前菜单的所有父菜单编号
     */
    @TableField("pcodes")
    private String pcodes;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * url地址
     */
    @TableField("url")
    private String url;

    /**
     * 菜单排序号
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 菜单层级
     */
    @TableField("levels")
    private Integer levels;

    /**
     * 是否是菜单(字典)
     */
    @TableField("menu_flag")
    private String menuFlag;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * 菜单状态(字典)
     */
    @TableField("status")
    private String status;

    /**
     * 是否打开新页面的标识(字典)
     */
    @TableField("new_page_flag")
    private String newPageFlag;

    /**
     * 是否打开(字典)
     */
    @TableField("open_flag")
    private String openFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private Long updateUser;

    @Override
    public String toString() {
        return "Menu{" +
        "menuId=" + menuId +
        ", code=" + code +
        ", pcode=" + pcode +
        ", pcodes=" + pcodes +
        ", name=" + name +
        ", icon=" + icon +
        ", url=" + url +
        ", sort=" + sort +
        ", levels=" + levels +
        ", menuFlag=" + menuFlag +
        ", description=" + description +
        ", status=" + status +
        ", newPageFlag=" + newPageFlag +
        ", openFlag=" + openFlag +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUser=" + createUser +
        ", updateUser=" + updateUser +
        "}";
    }
}
