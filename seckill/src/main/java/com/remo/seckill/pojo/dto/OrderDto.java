package com.remo.seckill.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDto {

    private Long id;

    private Long userId;

    private Long goodsId;

    private Long deliveryAddrId;

    private String goodsName;

    private Integer goodsCount;

    private Double goodsPrice;

    private Integer orderChannel;

    private Integer status;

    private LocalDateTime payDate;

    private Integer version;
}
