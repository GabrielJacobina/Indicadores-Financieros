package com.indicadoresfinancieros.document;


import java.math.BigDecimal;

public class Indicators {

    private BigDecimal dolar;
    private BigDecimal uf;
    private BigDecimal utm;

    public Indicators(BigDecimal dolar, BigDecimal uf, BigDecimal utm) {
        this.dolar = dolar;
        this.uf = uf;
        this.utm = utm;
    }

    public BigDecimal getDolar() {
        return dolar;
    }

    public void setDolar(BigDecimal dolar) {
        this.dolar = dolar;
    }

    public BigDecimal getUf() {
        return uf;
    }

    public void setUf(BigDecimal uf) {
        this.uf = uf;
    }

    public BigDecimal getUtm() {
        return utm;
    }

    public void setUtm(BigDecimal utm) {
        this.utm = utm;
    }
}
