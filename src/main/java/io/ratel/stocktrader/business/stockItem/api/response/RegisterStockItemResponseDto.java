package io.ratel.stocktrader.business.stockItem.api.response;

import io.ratel.stocktrader.business.stockItem.domain.entity.Market;
import io.ratel.stocktrader.business.stockItem.domain.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterStockItemResponseDto {

    /** 종목 코드 (6자 고정, 영문 대문자 + 숫자 조합) */
    private String itemCode;

    /** 종목 이름 (최대 100자, 한글 + 영문 대소문자 + 숫자) */
    private String itemName;

    /** 시장 구분 (KOSPI, KOSDAQ, KONEX, ELW, ETF, ETN, BOND) */
    private Market market;

    /** 테마 코드 (5자 고정, 영문 대문자 + 숫자 조합) */
    private Theme theme;

    /** 시장가 */
    private double price;
}
