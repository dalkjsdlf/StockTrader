package io.ratel.stocktrader.business.order.domain.entity;

/**
 * packageName  : io.ratel.stocktrader.business.order.domain.entity
 * fileName    : OrderStatus
 * author      : dorris
 * date        : 2025. 4. 12.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 4. 12.          dorris             최초생성
 */
public enum OrderStatus {
    NEW,           // 신규 주문
    SETTLED,      // 체결됨
    CANCELLED      // 취소됨
}
