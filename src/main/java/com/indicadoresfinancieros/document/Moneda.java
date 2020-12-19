package com.indicadoresfinancieros.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Moneda {

    @Id
    @JsonIgnore
    private String id;

    @Indexed(unique=true)
    private LocalDate date;

    private Indicators indicators;

    public Moneda() {
    }

    public Moneda(LocalDate date, Indicators indicators) {
        this.date = date;
        this.indicators = indicators;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
