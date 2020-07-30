package com.remo.seckill.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SeckillGoods")
@Table(name = "SECKILL_GOODS")
public class SeckillGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seckill_goods_id")
    private Long seckillGoodsId;

    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "stock_count")
    private Integer stockCount;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Version
    @Column(name = "version")
    private Integer version;
}
