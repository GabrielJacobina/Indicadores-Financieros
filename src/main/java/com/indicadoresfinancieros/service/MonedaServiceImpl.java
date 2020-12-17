package com.indicadoresfinancieros.service;

import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.repository.MonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MonedaServiceImpl implements MonedaService{

    @Autowired
    MonedaRepository monedaRepository;

    @Override
    public Flux<Moneda> findAll() {
        return monedaRepository.findAll();
    }

    @Override
    public Mono<Moneda> findById(String codigo) {
        return monedaRepository.findById(codigo);
    }

    @Override
    public Mono<Moneda> save(Moneda moneda) {
        return monedaRepository.save(moneda);
    }
}
