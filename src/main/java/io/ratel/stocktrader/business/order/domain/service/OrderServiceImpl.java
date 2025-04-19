package io.ratel.stocktrader.business.order.domain.service;

import io.ratel.stocktrader.business.order.domain.entity.Order;
import io.ratel.stocktrader.business.order.domain.entity.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public Order buyStock(Order order) {
        // TODO 구현시 삭제 테스트용: 주문 ID를 가짜로 할당하고 상태 설정
        return new Order(1L, OrderStatus.NEW);
    }
}
