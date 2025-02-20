package io.ratel.stocktrader.business.stockItem.domain.entity;

import lombok.Getter;

/**
 * packageName  : io.ratel.stocktrader.business.stockItem.domain.entity
 * fileName    : Theme
 * author      : dorris
 * date        : 2025. 2. 20.
 * description :
 * ================================================
 * DATE              AUTHOR              NOTE
 * ================================================
 * 2025. 2. 20.          dorris             최초생성
 */
@Getter
public enum Theme {
    IE001("IE001"), IE002("IE002"), IE003("IE003"), IE004("IE004"), IE005("IE005"),
    IE006("IE006"), IE007("IE007"), IE008("IE008"), IE009("IE009"), IE010("IE010"), IE011("IE011"),
    BH001("BH001"), BH002("BH002"), BH003("BH003"), BH004("BH004"), BH005("BH005"),
    BH006("BH006"), BH007("BH007"),
    EE001("EE001"), EE002("EE002"), EE003("EE003"), EE004("EE004"), EE005("EE005"),
    EE006("EE006"), EE007("EE007"),
    EC001("EC001"), EC002("EC002"), EC003("EC003"), EC004("EC004"),
    MC001("MC001"), MC002("MC002"), MC003("MC003"), MC004("MC004");

    private final String code;

    Theme(String code) {
        this.code = code;
    }

}
