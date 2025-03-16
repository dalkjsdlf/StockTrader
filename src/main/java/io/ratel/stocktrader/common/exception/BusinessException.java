package io.ratel.stocktrader.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * packageName  : io.ratel.stocktrader.common
 * fileName    : BusinessException
 * author      : dorris
 * date        : 2025. 3. 16.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 3. 16.          dorris             최초생성
 */
@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {

    private final BusinessErrorResult errorResult;
}
