package io.ratel.stocktrader.common.exception;

public record ErrorResponse(
        String code,
        String message
) {
}
