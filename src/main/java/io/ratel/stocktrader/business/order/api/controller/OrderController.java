package io.ratel.stocktrader.business.order.api.controller;

import io.ratel.stocktrader.business.order.api.request.BuyOrderRequest;
import io.ratel.stocktrader.business.order.api.request.SellOrderRequest;
import io.ratel.stocktrader.business.order.api.response.BuyOrderResponse;
import io.ratel.stocktrader.business.order.api.response.SellOrderResponse;
import io.ratel.stocktrader.business.order.domain.entity.Order;
import io.ratel.stocktrader.business.order.domain.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * 주식 매수 주문
     * @param BuyOrderRequest 주문 요청 DTO
     * @return 주문 결과 응답 DTO
     */
    @PostMapping("/buy")
    public ResponseEntity<BuyOrderResponse> placeBuyOrder(@Valid @RequestBody BuyOrderRequest buyOrderRequest) {
        Order order = buyOrderRequest.toDomain(); // DTO가 Domain으로 변환
        Order savedOrder = orderService.buyStock(order);
        BuyOrderResponse response = BuyOrderResponse.from(savedOrder); // Domain을 DTO로 변환
        return ResponseEntity.status(201).body(response);
    }

    /**
     * 주식 매도 주문
     * @param sellOrderRequest 주문 요청 DTO
     * @return 주문 결과 응답 DTO
     */
    @PostMapping("/sell")
    public ResponseEntity<SellOrderResponse> placeSellOrder(@Valid @RequestBody SellOrderRequest sellOrderRequest) {
        Order order = sellOrderRequest.toDomain(); // DTO → 도메인
        Order savedOrder = orderService.sellStock(order); // 도메인 처리
        SellOrderResponse response = SellOrderResponse.from(savedOrder); // 도메인 → DTO
        return ResponseEntity.status(201).body(response);
    }
}
