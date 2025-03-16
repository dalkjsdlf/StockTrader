package io.ratel.stocktrader.business.stockItem.domain.entity;

import io.ratel.stocktrader.common.entity.AuditableFields;
import jakarta.annotation.Nullable;
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
public class StockItem extends AuditableFields {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Column(nullable = false, length = 20, unique = true)
    private String itemCode;

    @Setter
    @Column(nullable = false, length = 100)
    private String itemName;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Market market;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Theme theme;

    @Setter
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

    public void update(String itemCode, String itemName, Market market, Theme theme, Double price) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.market = market;
        this.theme = theme;
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
