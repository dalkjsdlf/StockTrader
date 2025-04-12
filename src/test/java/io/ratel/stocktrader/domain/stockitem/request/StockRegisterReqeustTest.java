package io.ratel.stocktrader.domain.stockitem.request;

import io.ratel.stocktrader.business.stockItem.api.request.RegisterStockItemRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName  : io.ratel.stocktrader.domain.stockitem.request
 * fileName    : StockRegisterReqeustTest
 * author      : dorris
 * date        : 2025. 3. 30.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 3. 30.          dorris             최초생성
 */

public class StockRegisterReqeustTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("[성공] ITEMCODE 증명 실패하는 케이스에 대한 테스트")
    @ParameterizedTest(name = "[{index}] Invalid itemCode: \"{0}\"")
    @NullSource
    @ValueSource(strings = {
            "",              // 빈 문자열
            "     ",         // 공백만
            "abc12",         // 5글자
            "abc1234",       // 7글자
            "abc12!",        // 특수문자 포함
            "abcdef",        // 소문자만
            "ABCdef",        // 대소문자 혼합
            "12345!",        // 숫자+특수문자
            "ABC 12",        // 공백 포함
    })
    public void invalidItemCode_shouldFailValidation(String itemCode) {
        RegisterStockItemRequestDto dto = RegisterStockItemRequestDto.builder()
                .itemCode(itemCode)
                .build();

        Set<ConstraintViolation<RegisterStockItemRequestDto>> violations = validator.validate(dto);

        assertThat(violations)
                .anyMatch(v -> v.getPropertyPath().toString().equals("itemCode"));
    }

    @DisplayName("[성공] ITEMCODE 증명 성공하는 케이스에 대한 테스트")
    @ParameterizedTest(name = "[{index}] Valid itemCode: \"{0}\"")
    @ValueSource(strings = {
            "ABC123",
            "ZZZ999",
            "123ABC",
            "A1B2C3",
            "9Z8Y7X"
    })
    public void validItemCode_shouldPassValidation(String itemCode) {
        RegisterStockItemRequestDto dto = RegisterStockItemRequestDto.builder()
                .itemCode(itemCode)
                .build();

        Set<ConstraintViolation<RegisterStockItemRequestDto>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }
}
