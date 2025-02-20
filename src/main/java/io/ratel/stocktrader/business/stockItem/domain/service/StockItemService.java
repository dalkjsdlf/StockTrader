package io.ratel.stocktrader.business.stockItem.domain.service;

import io.ratel.stocktrader.business.stockItem.api.response.CreateStockItemResponseDto;
import io.ratel.stocktrader.business.stockItem.api.response.StockItemResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class StockItemService{

    /**
     * 전체 종목 목록 조회
     * @return 빈 리스트 반환 (추후 데이터 연동 예정)
     */
    public List<StockItemResponseDto> getAllItems() {
        return List.of();
    }

    /**
     * 새로운 종목 생성
     * @param createStockItemResponse 종목 생성 요청 DTO
     * @return null 반환 (추후 생성 로직 추가 예정)
     */
    public CreateStockItemResponseDto createStockItem(CreateStockItemResponseDto createStockItemResponse) {
        return null;
    }

    /**
     * 특정 종목 조회
     * @param id 종목 ID
     * @return null 반환 (추후 조회 로직 추가 예정)
     */
    public StockItemResponseDto getItemById(int id) {
        return null;
    }

    /**
     * 키워드를 기반으로 종목 검색
     * @param keyword 검색 키워드
     * @return 빈 리스트 반환 (추후 검색 로직 추가 예정)
     */
    public List<StockItemResponseDto> searchItems(String keyword) {
        return List.of();
    }

    /**
     * 특정 종목 상세 조회
     * @return 빈 리스트 반환 (추후 상세 조회 로직 추가 예정)
     */
    public List<StockItemResponseDto> getDeatilStockItem() {
        return List.of();
    }
}


