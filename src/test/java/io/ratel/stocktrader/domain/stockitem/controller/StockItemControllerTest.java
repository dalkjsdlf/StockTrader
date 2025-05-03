package io.ratel.stocktrader.domain.stockitem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ratel.stocktrader.business.stockItem.api.request.ModifyStockItemRequest;
import io.ratel.stocktrader.business.stockItem.api.request.RegisterStockItemRequest;
import io.ratel.stocktrader.business.stockItem.domain.entity.Market;
import io.ratel.stocktrader.business.stockItem.domain.entity.Theme;
import io.ratel.stocktrader.business.stockItem.domain.service.StockItemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName  : io.ratel.stocktrader.domain.stockitem.controller
 * fileName    : StockItemControllerTest
 * author      : dorris
 * date        : 2025. 3. 9.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 3. 9.          dorris             최초생성
 */
@DisplayName("StockItemController 테스트")
@SpringBootTest
public class StockItemControllerTest {

    private MockMvc mockMvc;

    private final StockItemService stockItemService;

    private ObjectMapper objectMapper;

    public StockItemControllerTest(@Autowired StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(StockItemService.class)
                .build();
        this.objectMapper = new ObjectMapper();

        RegisterStockItemRequest stock1 = RegisterStockItemRequest.builder()
                .itemCode("ABC123")
                .itemName("삼성전자")
                .market(Market.KOSPI)
                .theme(Theme.IE001)
                .price(50000)
                .build();

        RegisterStockItemRequest stock2 = RegisterStockItemRequest.builder()
                .itemCode("XYZ789")
                .itemName("카카오")
                .market(Market.KOSPI)
                .theme(Theme.IE002)
                .price(100000)
                .build();

        stockItemService.registerStockItem(stock1);
        stockItemService.registerStockItem(stock2);
    }

    @Test
    @DisplayName("[성공] 모든 종목 정보를 조회하면 200 OK와 데이터를 반환한다.")
    void givenStockItemsWhenGetAllStockItemsThenReturnStockList() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/stockItems"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].itemCode").value("ABC123"))
                .andExpect(jsonPath("$[1].itemCode").value("XYZ789"));
    }

    @Test
    @DisplayName("[성공] 새로운 종목을 등록하면 201 Created와 생성된 종목 정보를 반환한다.")
    void givenStockItemRequestWhenCreateStockItemThenReturnCreatedStockItem() throws Exception {
        // Given
        RegisterStockItemRequest request = RegisterStockItemRequest.builder()
                .itemCode("NAV333")
                .itemName("네이버")
                .market(Market.KOSPI)
                .theme(Theme.IE001)
                .price(20000)
                .build();

        // When & Then
        mockMvc.perform(post("/api/v1/stockItems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.itemCode").value("NAV333"))
                .andExpect(jsonPath("$.itemName").value("네이버"));
    }

    @Test
    @DisplayName("[실패] 중복된 종목 ID로 등록 시 400 Bad Request 반환")
    void givenDuplicateStockItemIdWhenCreateStockItemThenReturnBadRequest() throws Exception {
        // Given
        RegisterStockItemRequest duplicateRequest = RegisterStockItemRequest.builder()
                .itemCode("ABC123") // 이미 존재하는 종목 코드
                .itemName("네이버")
                .market(Market.KOSPI)
                .theme(Theme.IE001)
                .price(20000)
                .build();

        // When & Then
        mockMvc.perform(post("/api/v1/stockItems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(duplicateRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("[성공] 특정 종목 ID로 조회하면 200 OK와 종목 정보를 반환한다.")
    void givenValidStockIdWhenGetStockItemByIdThenReturnStockItem() throws Exception {
        // Given
        Long stockId = 1L;

        // When & Then
        mockMvc.perform(get("/api/v1/items/" + stockId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemCode").value("ABC123"));
    }

    @Test
    @DisplayName("[실패] 존재하지 않는 종목 ID로 조회하면 404 Not Found를 반환한다.")
    void givenInvalidStockIdWhenGetStockItemByIdThenReturnNotFound() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/items/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("[실패] 특정 종목 수정 시 존재하지 않는 종목이면 404 Not Found 반환")
    void givenInvalidStockIdWhenModifyStockItemThenReturnNotFound() throws Exception {
        // Given
        ModifyStockItemRequest modifyRequest = new ModifyStockItemRequest(
                "ZZZ999", "토스", Market.KONEX, Theme.BH001, 3000.0);

        // When & Then
        mockMvc.perform(patch("/api/v1/items/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modifyRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("[실패] 특정 종목 삭제 시 존재하지 않는 종목이면 404 Not Found 반환")
    void givenInvalidStockIdWhenDeleteStockItemThenReturnNotFound() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/v1/items/999"))
                .andExpect(status().isNotFound());
    }

    @AfterEach
    void tearDown() {
        stockItemService.removeAllItems();
    }
}