package io.ratel.stocktrader.business.stockItem.api.controller;

import io.ratel.stocktrader.business.stockItem.api.response.RegisterStockItemResponseDto;
import io.ratel.stocktrader.business.stockItem.api.response.StockItemResponse;
import io.ratel.stocktrader.business.stockItem.domain.service.StockItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * packageName  : io.ratel.stocktrader.business.stockItem.api.controller
 * fileName    : StockItemController
 * author      : dorris
 * date        : 2025. 2. 19.
 * description :
 * 종목 정보 관리 컨트롤러
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 2. 19.          dorris             최초생성
 */
@RestController
@RequestMapping("/api/v1/stockItems")
@RequiredArgsConstructor
public class StockItemController{

    private final StockItemService stockItemService;


    /**
     * 전체 종목 목록 조회
     * @return 빈 리스트 반환 (추후 서비스 로직 추가 예정)
     */
    @GetMapping
    public ResponseEntity<List<StockItemResponse>> getAllItems() {
        return ResponseEntity.ok(stockItemService.getAllItems());
    }

    /**
     * 새로운 종목 생성
     * @param createStockItemResponse 종목 생성 요청 DTO
     * @return 빈 CreateStockItemResponseDto 객체 반환
     */
    @PostMapping("")
    public ResponseEntity<RegisterStockItemResponseDto> createStockItem(@RequestBody RegisterStockItemResponseDto createStockItemResponse) {
        return ResponseEntity.ok(new RegisterStockItemResponseDto());
    }

    /**
     * 특정 종목 조회
     * @param id 종목 ID
     * @return 빈 StockItemResponseDto 객체 반환
     */
    @GetMapping("/{id}")
    public ResponseEntity<StockItemResponse> getItemById(@PathVariable int id) {
        return ResponseEntity.ok(new StockItemResponse());
    }

    /**
     * 키워드를 기반으로 종목 검색
     * @param keyword 검색 키워드
     * @return 빈 리스트 반환 (추후 서비스 로직 추가 예정)
     */
    @GetMapping("/search")
    public ResponseEntity<List<StockItemResponse>> searchItems(@RequestParam String keyword) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    /**
     * 특정 종목 상세 조회
     * @param id 종목 ID
     * @return 빈 리스트 반환 (추후 서비스 로직 추가 예정)
     */
    @GetMapping("/Detail/{id}")
    public ResponseEntity<List<StockItemResponse>> getDetailStockItem(@PathVariable int id) {
        return ResponseEntity.ok(Collections.emptyList());
    }
}

