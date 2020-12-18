package com.indicadoresfinancieros.document;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Dolar {

    @JsonProperty(value = "vl")
    private BigDecimal valor;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
