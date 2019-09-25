package com.user.pojo.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和菜单关联表
 *
 */
@Data
@TableName("sys_relation")
public class Relation implements Serializable {

    private static final long serialVersionUID = -1820661035591234422L;

    /**
     * 主键
     */
    @TableId(value = "relation_id", type = IdType.ID_WORKER)
    private Long relationId;

    /**
     * 菜单id
     */
    @TableField("menu_id")
    private Long menuId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    @Override
    public String toString() {
        return "Relation{" +
        "relationId=" + relationId +
        ", menuId=" + menuId +
        ", roleId=" + roleId +
        "}";
    }
}
