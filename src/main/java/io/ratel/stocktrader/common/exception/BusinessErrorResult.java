package io.ratel.stocktrader.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * packageName  : io.ratel.stocktrader.common.exception
 * fileName    : BusinessErrorResult
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
public enum BusinessErrorResult {
    NOT_FOUND_ITEM(HttpStatus.BAD_REQUEST, "The item is not found"),
    WRONG_USER_ID(HttpStatus.BAD_REQUEST, "The ID was entered incorrectly."),
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Exception"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

