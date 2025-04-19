package io.ratel.stocktrader.business.order.api.response;

import io.ratel.stocktrader.business.order.domain.entity.Order;

public class SellOrderResponse {
    private Long orderId;
    private String status;

    public SellOrderResponse(Long orderId, String name) {
        this.orderId = orderId;
        this.status = status;
    }


    /**
     * Order → BuyOrderResponse 변환
     */
    public static SellOrderResponse from(Order order) {
        return new SellOrderResponse(
                order.getOrderId(),
                order.getOrderStatus().name()
        );
    }
}
