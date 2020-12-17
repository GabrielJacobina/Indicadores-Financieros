package com.indicadoresfinancieros.controller;

import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.service.MonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class MonedaController {

    @Autowired
    MonedaService monedaService;

    @GetMapping
    public Flux<Moneda> getMoneda(){
        return monedaService.findAll();
    }


    @GetMapping(value="/{codigo}")
    public Mono<Moneda> getMonedaId(@PathVariable String codigo){
        return monedaService.findById(codigo);
    }

    @PostMapping
    public Mono<Moneda> save(@RequestBody Moneda moneda){
        return monedaService.save(moneda);
    }
}
