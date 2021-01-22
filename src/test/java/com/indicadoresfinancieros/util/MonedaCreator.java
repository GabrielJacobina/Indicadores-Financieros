package com.indicadoresfinancieros.util;

import com.indicadoresfinancieros.document.Indicators;
import com.indicadoresfinancieros.document.Moneda;

import java.math.BigDecimal;

public class MonedaCreator {

    public static Moneda createMonedaToBeSaved() {
        return Moneda.builder()
                .indicators(new Indicators(new BigDecimal("5.4"),new BigDecimal("7.34"), new BigDecimal("6.26")))
                .build();
    }

    public static Moneda createValidMoneda() {
        return Moneda.builder()
                .date("10-01-2021")
                .indicators(new Indicators(new BigDecimal("5.4"),new BigDecimal("7.34"), new BigDecimal("6.26")))
                .build();
    }
}
