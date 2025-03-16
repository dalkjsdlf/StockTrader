package io.ratel.stocktrader.business.stockItem.domain.repository;

import io.ratel.stocktrader.business.stockItem.domain.entity.StockItem;

import java.util.List;
import java.util.Optional;

/**
 * packageName  : io.ratel.stocktrader.business.stockItem.domain.repository
 * fileName    : StockItemRepository
 * author      : dorris
 * date        : 2025. 3. 9.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 3. 9.          dorris             최초생성
 */
public interface StockItemRepository {

    List<StockItem> findAll();

    Optional<StockItem> findById(Long id);

    StockItem save(StockItem stockItem);

    void deleteById(Long id);

    void deleteAll();

    List<StockItem> findByKeyword(String itemCode);

}
