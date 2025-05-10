package io.ratel.stocktrader.business.order.domain.entity;

public enum OrderStatus {
    NEW,           // 신규 주문
    SETTLED,      // 체결됨
    CANCELLED      // 취소됨
}
