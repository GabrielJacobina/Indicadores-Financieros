package com.indicadoresfinancieros.document;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Serie {

    private LocalDate fecha;
    private BigDecimal valor;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
