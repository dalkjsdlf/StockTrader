package io.ratel.stocktrader.business.order.api.response;

import io.ratel.stocktrader.business.order.domain.entity.Order;

public class BuyOrderResponse {
    private Long orderId;
    private String status;

    public BuyOrderResponse(Long orderId, String name) {
        this.orderId = orderId;
        this.status = status;
    }


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
