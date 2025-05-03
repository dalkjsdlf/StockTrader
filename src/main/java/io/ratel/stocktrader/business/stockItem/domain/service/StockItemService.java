package io.ratel.stocktrader.business.stockItem.domain.service;

import io.ratel.stocktrader.business.stockItem.api.request.ModifyStockItemRequest;
import io.ratel.stocktrader.business.stockItem.api.request.RegisterStockItemRequest;
import io.ratel.stocktrader.business.stockItem.api.response.RegisterStockItemResponseDto;
import io.ratel.stocktrader.business.stockItem.api.response.StockItemResponse;
import io.ratel.stocktrader.business.stockItem.domain.entity.StockItem;
import io.ratel.stocktrader.business.stockItem.domain.repository.StockItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName  : io.ratel.stocktrader.business.stockItem.domain.service
 * fileName    : StockItemService
 * author      : dorris
 * date        : 2025. 2. 20.
 * description :
 * 종목 정보 관리 서비스
 * 종목 정보를 조회 및 생성하는 비즈니스 로직을 담당
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 2. 20.          dorris             최초생성
 */
@Service
@RequiredArgsConstructor
public class StockItemService{

    private final StockItemRepository stockItemRepository;

    /**
     * 전체 종목 목록 조회
     * @return 빈 리스트 반환 (추후 데이터 연동 예정)
     */
    public List<StockItemResponse> getAllItems() {
        List<StockItem> stockItems = stockItemRepository.findAll();
        /*TODO REFACTORING : ENTITY TO DTO는 DTO에 로직을 넣는다.*/
        return stockItems.stream().map(stock->
                  StockItemResponse.builder().
                    itemCode(stock.getItemCode()).
                          itemName(stock.getItemName()).
                          market(stock.getMarket()).
                          theme(stock.getTheme()).
                          price(stock.getPrice()).
                    build()
        ).collect(Collectors.toList());
    }

    /**
     * 새로운 종목 생성
     * @param registerStockItemRequest 종목 생성 요청 DTO
     * @return null 반환 (추후 생성 로직 추가 예정)
     */
    public RegisterStockItemResponseDto registerStockItem(RegisterStockItemRequest registerStockItemRequest) {

        StockItem newStockItem = new StockItem(null,
                registerStockItemRequest.getItemCode(),
                registerStockItemRequest.getItemName(),
                registerStockItemRequest.getMarket(),
                registerStockItemRequest.getTheme(),
                registerStockItemRequest.getPrice());

        StockItem savedStockItem = stockItemRepository.save(newStockItem);
        /*TODO REFACTORING : ENTITY TO DTO는 DTO에 로직을 넣는다.*/
        return RegisterStockItemResponseDto.builder()
                .itemCode(savedStockItem.getItemCode())
                .itemName(savedStockItem.getItemName())
                .market(savedStockItem.getMarket())
                .theme(savedStockItem.getTheme())
                .price(savedStockItem.getPrice())
                .build();
    }

    /**
     * 특정 종목 조회
     * @param id 종목 ID
     * @return null 반환 (추후 조회 로직 추가 예정)
     */
    public StockItemResponse getItemById(Long id) {

        StockItem stockItem = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found"));
        /*TODO REFACTORING : ENTITY TO DTO는 DTO에 로직을 넣는다.*/
        return StockItemResponse.builder()
                .itemCode(stockItem.getItemCode())
                .itemName(stockItem.getItemName())
                .market(stockItem.getMarket())
                .theme(stockItem.getTheme())
                .price(stockItem.getPrice())
                .build();
    }

    /**
     * 키워드를 기반으로 종목 검색
     * @param keyword 검색 키워드
     * @return 빈 리스트 반환 (추후 검색 로직 추가 예정)
     */
    public List<StockItemResponse> searchItems(String keyword) {

        List<StockItem> stockItems = stockItemRepository.findByKeyword(keyword);

        return stockItems.stream().map(stock->
                StockItemResponse.builder().
                        itemCode(stock.getItemCode()).
                        itemName(stock.getItemName()).
                        market(stock.getMarket()).
                        theme(stock.getTheme()).
                        price(stock.getPrice()).
                        build()
        ).collect(Collectors.toList());
    }

    /**
     * 모든 종목 삭제
     */
    public void removeAllItems() {
        stockItemRepository.deleteAll();
    }

    /**
     * 특정 종목 삭제
     * @param id 종목 ID
     */
    public void removeItemById(Long id) {
        StockItem stockItem = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found"));
        stockItemRepository.deleteById(id);
    }

    /**
     * 종목 정보 수정
     * @param id 종목 ID
     * @param requestDto 수정할 정보가 담긴 DTO
     * @return 수정된 종목 정보 반환
     */
    public StockItemResponse modifyItem(Long id, ModifyStockItemRequest requestDto) {
        StockItem stockItem = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found"));

        stockItem.update(
                requestDto.getItemCode(),
                requestDto.getItemName(),
                requestDto.getMarket(),
                requestDto.getTheme(),
                requestDto.getPrice()
        );

        StockItem updatedStockItem = stockItemRepository.save(stockItem);

        /*TODO REFACTORING : ENTITY TO DTO는 DTO에 로직을 넣는다.*/
        return StockItemResponse.builder()
                .itemCode(updatedStockItem.getItemCode())
                .itemName(updatedStockItem.getItemName())
                .market(updatedStockItem.getMarket())
                .theme(updatedStockItem.getTheme())
                .price(updatedStockItem.getPrice())
                .build();
    }
}


