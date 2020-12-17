package com.indicadoresfinancieros.service;

import com.indicadoresfinancieros.document.Moneda;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MonedaService {

    Flux<Moneda> findAll();
    Mono<Moneda> findById(String codigo);
    Mono<Moneda> save(Moneda moneda);
}
