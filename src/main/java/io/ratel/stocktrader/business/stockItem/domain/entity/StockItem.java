package io.ratel.stocktrader.business.stockItem.domain.entity;

import io.ratel.stocktrader.common.entity.BaseEntiry;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


/**
 * packageName  : io.ratel.stocktrader.business.domain.stockitem.entity
 * fileName    : StockItem
 * author      : dorris
 * date        : 2025. 2. 19.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 2. 19.          dorris             최초생성
 */
@Entity
@Getter
public class StockItem extends BaseEntiry {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String itemCode;

    @Column(nullable = false, length = 100)
    private String itemName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Market market;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Theme theme;

    @Column(nullable = false)
    private double price;

    protected StockItem() {}

    public StockItem(Long id, String itemCode, String itemName, Market market, Theme theme, double price) {
        this.id = id;
        this.theme = theme;
        this.market = market;
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.price = price;
    }

    public void updateItemName(String itemName, Market market, Theme theme, Double price) {
        this.itemName = itemName;
    }

    public void updatePrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItem stockItem = (StockItem) o;
        return Objects.equals(id, stockItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
