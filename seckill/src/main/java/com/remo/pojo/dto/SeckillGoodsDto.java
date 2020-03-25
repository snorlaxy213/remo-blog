package com.remo.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SeckillGoodsDto {

    private Long seckillGoodsId;

    private Long goodsId;

    private Integer stockCount;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer version;

    private GoodsDto goodsDto;
}
