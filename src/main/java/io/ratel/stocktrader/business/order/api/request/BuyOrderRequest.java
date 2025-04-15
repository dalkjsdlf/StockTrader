package io.ratel.stocktrader.business.order.api.request;

import io.ratel.stocktrader.business.order.domain.entity.Order;
import io.ratel.stocktrader.business.order.domain.entity.OrderType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
public class BuyOrderRequest {
    @NotNull(message = "종목 ID는 필수입니다.")
    private Long itemId;

    @NotNull(message = "주문 수량은 필수입니다.")
    @Min(value = 1, message = "주문 수량은 1 이상이어야 합니다.")
    private Integer quantity;

    @NotNull(message = "주문 가격은 필수입니다.")
    @DecimalMin(value = "0.0", inclusive = false, message = "가격은 0보다 커야 합니다.")
    private Double price;

    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;

    @NotBlank(message = "계좌 ID는 필수입니다.")
    private Long accountId;

    /**
     * BuyOrderRequest → Order 변환
     */
    public Order toDomain() {
        return Order.ofBuyOrder(
                this.userId,                // memberId
                this.itemId,                // itemId
                this.accountId, // accountId (문자열 → 숫자 변환)
                this.quantity,               // 수량
                OrderType.LIMIT              // 현재는 LIMIT 하드코딩 (필요시 필드 추가)
        );
    }
}
