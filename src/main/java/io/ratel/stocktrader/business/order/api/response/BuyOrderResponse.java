package io.ratel.stocktrader.business.order.api.response;

import io.ratel.stocktrader.business.order.domain.entity.Order;
import lombok.Getter;

@Getter
public record BuyOrderResponse(Long orderId, String status) {
    /**
     * Order → BuyOrderResponse 변환
     */
    public static BuyOrderResponse from(Order order) {
        return new BuyOrderResponse(
                order.getOrderId(),
                order.getOrderStatus().name()
        );
    }
}
