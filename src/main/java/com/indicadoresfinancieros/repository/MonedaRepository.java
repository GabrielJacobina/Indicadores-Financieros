package com.indicadoresfinancieros.repository;

import com.indicadoresfinancieros.document.Moneda;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MonedaRepository extends ReactiveMongoRepository<Moneda, String> {
}
