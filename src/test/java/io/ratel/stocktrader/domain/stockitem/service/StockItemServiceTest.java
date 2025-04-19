package io.ratel.stocktrader.domain.stockitem.service;

import io.ratel.stocktrader.business.stockItem.api.request.ModifyStockItemRequest;
import io.ratel.stocktrader.business.stockItem.api.request.RegisterStockItemRequest;
import io.ratel.stocktrader.business.stockItem.api.response.RegisterStockItemResponseDto;
import io.ratel.stocktrader.business.stockItem.api.response.StockItemResponse;
import io.ratel.stocktrader.business.stockItem.domain.entity.StockItem;
import io.ratel.stocktrader.business.stockItem.domain.entity.Market;
import io.ratel.stocktrader.business.stockItem.domain.entity.Theme;
import io.ratel.stocktrader.business.stockItem.domain.repository.StockItemRepositoryStub;
import io.ratel.stocktrader.business.stockItem.domain.service.StockItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("StockItemService Stub 기반 테스트")
class StockItemServiceTest {

    private StockItemRepositoryStub stockItemRepositoryStub;
    private StockItemService stockItemService;

    @BeforeEach
    void setUp() {
        stockItemRepositoryStub = new StockItemRepositoryStub();
        stockItemService = new StockItemService(stockItemRepositoryStub);

        // 기본 데이터 추가
        stockItemRepositoryStub.save(new StockItem(null, "ABC123", "삼성전자", Market.KOSPI, Theme.IE001, 1500.0));
        stockItemRepositoryStub.save(new StockItem(null, "XYZ789", "네이버", Market.KOSDAQ, Theme.IE002, 1800.0));
    }

    @Test
    @DisplayName("[성공] 모든 종목 목록 조회하면 StockItemResponse 리스트 반환")
    void givenStockItemsWhenGetAllItemsThenReturnStockItemResponseList() {
        // When
        List<StockItemResponse> result = stockItemService.getAllItems();

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getItemCode()).isEqualTo("ABC123");
        assertThat(result.get(1).getItemCode()).isEqualTo("XYZ789");
    }

    @Test
    @DisplayName("[성공] 새로운 종목을 등록하면 등록된 StockItemResponse 반환")
    void givenCreateStockItemRequestWhenRegisterStockItemThenReturnRegisteredStockItemResponse() {
        // Given
        RegisterStockItemRequest request = RegisterStockItemRequest.builder()
                .itemCode("DEF456")
                .itemName("카카오")
                .market(Market.KOSPI)
                .theme(Theme.IE003)
                .price(2000.0)
                .build();

        // When
        RegisterStockItemResponseDto response = stockItemService.registerStockItem(request);

        // Then
        assertThat(response.getItemCode()).isEqualTo("DEF456");
        assertThat(stockItemRepositoryStub.findAll()).hasSize(3);
    }

    @Test
    @DisplayName("[성공] 특정 종목 ID로 조회하면 StockItemResponse 반환")
    void givenValidItemIdWhenGetItemByIdThenReturnStockItemResponse() {
        // When
        StockItemResponse result = stockItemService.getItemById(1L);

        // Then
        assertThat(result.getItemCode()).isEqualTo("ABC123");
    }

    @Test
    @DisplayName("[실패] 존재하지 않는 종목 ID로 조회하면 예외 발생")
    void givenInvalidItemIdWhenGetItemByIdThenThrowException() {
        // When & Then
        assertThatThrownBy(() -> stockItemService.getItemById(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Stock item not found");
    }

    @Test
    @DisplayName("[성공] 특정 종목 ID로 삭제하면 정상 삭제됨")
    void givenValidItemIdWhenRemoveItemByIdThenDeleteStockItem() {
        // When
        stockItemService.removeItemById(1L);

        // Then
        assertThat(stockItemRepositoryStub.findAll()).hasSize(1);
    }

    @Test
    @DisplayName("[실패] 존재하지 않는 종목 ID로 삭제 시 예외 발생")
    void givenInvalidItemIdWhenRemoveItemByIdThenThrowException() {
        // When & Then
        assertThatThrownBy(() -> stockItemService.removeItemById(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Stock item not found");
    }

    @Test
    @DisplayName("[성공] 모든 종목을 삭제하면 정상 삭제됨")
    void whenRemoveAllItemsThenDeleteAllStockItems() {
        // When
        stockItemService.removeAllItems();

        // Then
        assertThat(stockItemRepositoryStub.findAll()).isEmpty();
    }

    @Test
    @DisplayName("[성공] 특정 종목 ID로 수정하면 수정된 StockItemResponse 반환")
    void givenValidItemIdWhenModifyItemThenUpdateAndReturnStockItemResponse() {
        // Given
        ModifyStockItemRequest requestDto = new ModifyStockItemRequest("XYZ789", "카카오", Market.KOSPI, Theme.IE003, 2100.0);

        // When
        StockItemResponse result = stockItemService.modifyItem(2L, requestDto);

        // Then
        assertThat(result.getItemName()).isEqualTo("카카오");
        assertThat(result.getPrice()).isEqualTo(2100.0);
    }

    @Test
    @DisplayName("[실패] 존재하지 않는 종목 ID로 수정 시 예외 발생")
    void givenInvalidItemIdWhenModifyItemThenThrowException() {
        // Given
        ModifyStockItemRequest requestDto = new ModifyStockItemRequest("ZZZ999", "토스", Market.KONEX, Theme.BH001, 3000.0);

        // When & Then
        assertThatThrownBy(() -> stockItemService.modifyItem(999L, requestDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Stock item not found");
    }
}