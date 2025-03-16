package io.ratel.stocktrader.business.stockItem.domain.repository;

import io.ratel.stocktrader.business.stockItem.domain.entity.StockItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * packageName  : io.ratel.stocktrader.business.stockItem.domain.repository
 * fileName    : StockItemRepositoryStub
 * author      : dorris
 * date        : 2025. 3. 9.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 3. 9.          dorris             최초생성
 */
public class StockItemRepositoryStub implements StockItemRepository{
    private final Map<Long, StockItem> stockItemMap = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<StockItem> findAll() {
        return new ArrayList<>(stockItemMap.values());
    }

    @Override
    public Optional<StockItem> findById(Long id) {
        return Optional.ofNullable(stockItemMap.get(id));
    }

    @Override
    public StockItem save(StockItem stockItem) {
        if (stockItem.getId() == null) {
            stockItem = new StockItem(
                    idGenerator.getAndIncrement(),
                    stockItem.getItemCode(),
                    stockItem.getItemName(),
                    stockItem.getMarket(),
                    stockItem.getTheme(),
                    stockItem.getPrice()
            );
        }
        stockItemMap.put(stockItem.getId(), stockItem);
        return stockItem;
    }

    @Override
    public void deleteById(Long id) {
        stockItemMap.remove(id);
    }

    @Override
    public void deleteAll() {
        stockItemMap.clear();
    }

    @Override
    public List<StockItem> findByKeyword(String keyword) {
        return stockItemMap.values().stream()
                .filter(stockItem -> stockItem.getItemCode().contains(keyword))
                .toList();
    }
}
