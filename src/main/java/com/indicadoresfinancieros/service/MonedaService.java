package com.indicadoresfinancieros.service;

import com.indicadoresfinancieros.document.Moneda;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface MonedaService {
    Mono<Moneda> obtendoMoneda(LocalDate date);
}
