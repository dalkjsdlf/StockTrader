package io.ratel.stocktrader.business.stockItem.domain.repository;

import io.ratel.stocktrader.business.stockItem.domain.entity.StockItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
@Repository
@RequiredArgsConstructor
public class StockItemRepositoryImpl implements StockItemRepository {

    private final StockItemJpaRepository stockItemJpaRepository;

    @Override
    public List<StockItem> findAll() {
        return List.of();
    }

    @Override
    public Optional<StockItem> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public StockItem save(StockItem stockItem) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<StockItem> findByKeyword(String itemCode) {
        return List.of();
    }
}
