package com.remo.seckill.service.impl;

import com.remo.seckill.pojo.dto.GoodsDto;
import com.remo.seckill.pojo.dto.OrderDto;
import com.remo.seckill.pojo.dto.SeckillGoodsDto;
import com.remo.seckill.pojo.po.Order;
import com.remo.seckill.pojo.po.SeckillOrder;
import com.remo.seckill.repository.OrderRepository;
import com.remo.seckill.repository.SeckillOrderRepository;
import com.remo.seckill.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service("orderServiceImpl")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class OrderServiceImpl implements OrderService {

    @Resource(name = "orderRepository")
    OrderRepository orderRepository;

    @Resource(name = "seckillOrderRepository")
    SeckillOrderRepository seckillOrderRepository;

    @Override
    @Transactional
    public OrderDto createOrder(Long userId, SeckillGoodsDto seckillGoodsDto) {
        GoodsDto goodsDto = seckillGoodsDto.getGoodsDto();

        Order order = new Order();
        order.setUserId(userId);
        order.setGoodsId(seckillGoodsDto.getGoodsId());
        order.setGoodsName(goodsDto.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goodsDto.getGoodsPrice());
        order.setPayDate(LocalDateTime.now());
        order.setStatus(1);
        order.setCreateTime(LocalDateTime.now());
        orderRepository.save(order);

        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(userId);
        seckillOrder.setGoodsId(goodsDto.getGoodsId());
        seckillOrder.setOrderId(order.getId());
        seckillOrderRepository.save(seckillOrder);

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }
}
