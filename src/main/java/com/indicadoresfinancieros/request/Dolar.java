package com.indicadoresfinancieros.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Dolar {

    @JsonProperty(value = "vl")
    private BigDecimal valor;
}
