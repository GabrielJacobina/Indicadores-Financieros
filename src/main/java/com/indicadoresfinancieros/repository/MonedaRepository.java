package com.indicadoresfinancieros.repository;

import com.indicadoresfinancieros.document.Moneda;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface MonedaRepository extends ReactiveMongoRepository<Moneda, String> {

    Mono<Moneda> findMonedaByDate (LocalDateTime date);

}
