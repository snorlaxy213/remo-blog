package com.remo.seckill.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "Goods")
@Table(name = "GOODS")
public class Goods {

    /**
     * -AUTO主键由程序控制, 是默认选项 ,不设置就是这个
     * <p>
     * -IDENTITY 主键由数据库生成, 采用数据库自增长, Oracle不支持这种方式
     * <p>
     * -SEQUENCE 通过数据库的序列产生主键, MYSQL  不支持-----需要对应于同名的主键生成器@SequenceGenerator和@TableGenerator
     * <p>
     * -Table 提供特定的数据库产生主键, 该方式更有利于数据库的移植------需要对应于同名的主键生成器@SequenceGenerator和@TableGenerator
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_title")
    private String goodsTitle;

    @Column(name = "goods_img")
    private String goodsImg;

    @Column(name = "goods_detail")
    private String goodsDetail;

    @Column(name = "goods_price")
    private Double goodsPrice;

    @Column(name = "goods_stock")
    private Integer goodsStock;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Version
    @Column(name = "version")
    private Integer version;
}
