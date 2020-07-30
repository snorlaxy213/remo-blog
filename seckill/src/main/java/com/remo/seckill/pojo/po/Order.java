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
@Entity(name = "Order")
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "delivery_addr_id")
    private Long deliveryAddrId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_count")
    private Integer goodsCount;

    @Column(name = "goods_price")
    private Double goodsPrice;

    @Column(name = "order_channel")
    private Integer orderChannel;

    @Column(name = "status")
    private Integer status;

    @Column(name = "pay_date")
    private LocalDateTime payDate;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Version
    @Column(name = "version")
    private Integer version;
}
