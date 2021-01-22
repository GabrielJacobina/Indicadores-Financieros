package com.indicadoresfinancieros.document;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Indicators {

    private BigDecimal dolar;
    private BigDecimal uf;
    private BigDecimal utm;
}
