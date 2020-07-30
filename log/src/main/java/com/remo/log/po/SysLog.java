package com.remo.log.po;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity(name = "log")
@Table(name = "LOG")
public class SysLog implements Serializable {

    private static final long serialVersionUID = -413824666728167766L;

    /**
     * TABLE：使用一个特定的数据库表格来保存主键。
     * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * IDENTITY：主键由数据库自动生成（主要是自动增长型）
     * AUTO：主键由程序控制。
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "operation")
    private String operation;

    @Column(name = "time")
    private Integer time;

    @Column(name = "method")
    private String method;

    @Column(name = "params")
    private String params;

    @Column(name = "ip")
    private String ip;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_user")
    private String updateUser;
}
