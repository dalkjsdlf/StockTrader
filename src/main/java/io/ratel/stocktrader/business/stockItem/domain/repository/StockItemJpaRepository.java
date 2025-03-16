package io.ratel.stocktrader.business.stockItem.domain.repository;

import io.ratel.stocktrader.business.stockItem.domain.entity.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName  : io.ratel.stocktrader.business.stockItem.domain.repository
 * fileName    : StockItemJpaRepository
 * author      : dorris
 * date        : 2025. 3. 16.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 3. 16.          dorris             최초생성
 */
public interface StockItemJpaRepository extends JpaRepository<StockItem, Long> {

}
