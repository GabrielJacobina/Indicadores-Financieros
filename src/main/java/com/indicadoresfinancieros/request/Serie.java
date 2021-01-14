package com.indicadoresfinancieros.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Serie {

    private LocalDate fecha;
    private BigDecimal valor;
}
