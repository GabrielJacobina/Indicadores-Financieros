package com.indicadoresfinancieros.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Moneda {

    @Id
    private String codigo;
    private LocalDate date;

    private Indicators indicators;

    public Moneda() {
    }

    public Moneda(String codigo, LocalDate date, Indicators indicators) {
        this.codigo = codigo;
        this.date = date;
        this.indicators = indicators;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Indicators getIndicators() {
        return indicators;
    }

    public void setIndicators(Indicators indicators) {
        this.indicators = indicators;
    }
}
