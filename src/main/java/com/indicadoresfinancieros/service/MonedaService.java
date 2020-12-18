package com.indicadoresfinancieros.service;

import com.indicadoresfinancieros.document.Dolar;
import com.indicadoresfinancieros.document.MindicadorApi;
import com.indicadoresfinancieros.document.Moneda;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MonedaService {

    Flux<Moneda> findAll();
    Mono<Moneda> findById(String codigo);
    Mono<Moneda> save(Moneda moneda);
    MindicadorApi obterMoeda(String url);
    MindicadorApi testeDeUf();
    MindicadorApi testeDeUtm();
    ResponseEntity<Dolar> testeDeDolar();
}
