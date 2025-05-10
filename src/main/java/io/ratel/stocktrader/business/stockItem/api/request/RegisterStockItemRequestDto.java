package io.ratel.stocktrader.business.stockItem.api.request;

import io.ratel.stocktrader.business.stockItem.domain.entity.Market;
import io.ratel.stocktrader.business.stockItem.domain.entity.Theme;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Pattern;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterStockItemRequestDto {

    /** 종목 코드 (6자 고정, 영문 대문자 + 숫자 조합) */
    @NotBlank(message = "종목 코드는 필수 입력 값입니다.")
    @Size(min = 6, max = 6, message = "종목 코드는 6글자여야 합니다.")
    @Pattern( regexp = "^[A-Z0-9]{6}$", message = "종목 코드는 영문 대문자와 숫자로만 구성되어야 합니다.")
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
