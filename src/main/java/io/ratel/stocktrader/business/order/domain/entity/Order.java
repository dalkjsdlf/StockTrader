package io.ratel.stocktrader.business.order.domain.entity;

import io.ratel.stocktrader.common.entity.BaseEntiry;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * packageName  : io.ratel.stocktrader.business.order.domain.entity
 * fileName    : Order
 * author      : dorris
 * date        : 2025. 4. 12.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 4. 12.          dorris             최초생성
 */
@Entity
@Table(name = "order")
@Getter
@NoArgsConstructor
public class Order extends BaseEntiry {

    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;


    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "item_id", nullable = false)
    private Integer itemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "trade_type", length = 10, nullable = false)
    private TradeType tradeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", length = 10, nullable = false)
    private OrderType orderType;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 20, nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "settlement_date")
    private LocalDateTime settlementDate;

    // ✅ 생성자 (Entity 내부 생성 용)
    public Order(Integer memberId, Integer itemId, TradeType tradeType, OrderType orderType,
                 Integer quantity, Integer accountId, OrderStatus orderStatus, LocalDateTime orderDate) {
        this.memberId = memberId;
        this.itemId = itemId;
        this.tradeType = tradeType;
        this.orderType = orderType;
        this.quantity = quantity;
        this.accountId = accountId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    // ✅ 매수 주문용 팩토리 메서드
    public static Order ofBuyOrder(Integer memberId, Integer itemId, Integer accountId, Integer quantity, OrderType orderType) {
        return new Order(
                memberId,
                itemId,
                TradeType.BUY,
                orderType,
                quantity,
                accountId,
                io.ratel.stocktrader.business.order.domain.entity.OrderStatus.NEW,
                LocalDateTime.now()
        );
    }

    // ✅ 매도 주문용 팩토리 메서드
    public static Order ofSellOrder(Integer memberId, Integer itemId, Integer accountId, Integer quantity, OrderType orderType) {
        return new Order(
                memberId,
                itemId,
                TradeType.SELL,
                orderType,
                quantity,
                accountId,
                io.ratel.stocktrader.business.order.domain.entity.OrderStatus.NEW,
                LocalDateTime.now()
        );
    }

    /*
    * TODO 인터페이스 검토 이후 구현시 삭제
    * */
    public Order(Long orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
