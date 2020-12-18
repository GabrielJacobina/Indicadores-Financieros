package com.indicadoresfinancieros.document;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Moneda {

    private LocalDate date;

    private Indicators indicators;

    public Moneda() {
    }

    public Moneda(LocalDate date, Indicators indicators) {
        this.date = date;
        this.indicators = indicators;
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
