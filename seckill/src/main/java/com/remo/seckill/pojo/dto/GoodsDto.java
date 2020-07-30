package com.remo.seckill.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GoodsDto {

    private Long goodsId;

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private String goodsDetail;

    private Double goodsPrice;

    private Integer goodsStock;

    private Integer version;
}
