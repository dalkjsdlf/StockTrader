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
    private String ItemCode;

    @Setter
    @Column(nullable = false, length = 100)
    private String ItemName;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Market market;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Theme theme;

    protected StockItem() {}


}
