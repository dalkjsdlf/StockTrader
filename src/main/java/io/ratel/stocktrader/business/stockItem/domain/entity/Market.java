package io.ratel.stocktrader.business.stockItem.domain.entity;

import lombok.Getter;

/**
 * packageName  : io.ratel.stocktrader.business.stockItem.domain.entity
 * fileName    : Market
 * author      : dorris
 * date        : 2025. 2. 20.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 2. 20.          dorris             최초생성
 */
@Getter
public enum Market {
    KOSPI("KOSPI", "코스피"),
    KOSDAQ("KOSDAQ", "코스닥"),
    KONEX("KONEX", "코넥스"),
    ELW("ELW", "주식워런트증권"),
    ETF("ETF", "상장지수펀드"),
    ETN("ETN", "상장지수증권"),
    BOND("BOND", "채권");

    private final String code;
    private final String description;

    Market(String code, String description) {
        this.code = code;
        this.description = description;
    }

}