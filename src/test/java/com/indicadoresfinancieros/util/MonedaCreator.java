package com.indicadoresfinancieros.util;

import com.indicadoresfinancieros.document.Indicators;
import com.indicadoresfinancieros.document.Moneda;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonedaCreator {

    public static Moneda createMonedaToBeSaved() {
        return Moneda.builder()
                .date(LocalDate.now().toString())
                .indicators(new Indicators(new BigDecimal("5.4"),new BigDecimal("7.34"), new BigDecimal("6.26")))
                .build();
    }

    public static Moneda createValidMoneda() {
        return Moneda.builder()
                .date("2021-01-01")
                .indicators(new Indicators(new BigDecimal("29069.39"),new BigDecimal("50978"), new BigDecimal("717.400857")))
                .build();
    }
}
