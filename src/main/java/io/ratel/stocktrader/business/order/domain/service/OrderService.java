package io.ratel.stocktrader.business.order.domain.service;

import io.ratel.stocktrader.business.order.domain.entity.Order;

public interface OrderService {
    public Order buyStock(Order order);
}
