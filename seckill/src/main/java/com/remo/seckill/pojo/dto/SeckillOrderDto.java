package com.remo.seckill.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SeckillOrderDto {

    private Long id;

    private Long userId;

    private Long orderId;

    private Long goodsId;
}
