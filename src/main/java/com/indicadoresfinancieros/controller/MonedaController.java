package com.indicadoresfinancieros.controller;

import com.indicadoresfinancieros.document.Dolar;
import com.indicadoresfinancieros.document.MindicadorApi;
import com.indicadoresfinancieros.document.Moneda;
import com.indicadoresfinancieros.service.MonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class MonedaController {

    @Autowired
    MonedaService monedaService;

    /*@GetMapping
    public Flux<Moneda> getMoneda(){
        return monedaService.findAll();
    }*/

//    @GetMapping
//    public ResponseEntity<Moneda> obterMoneda() {
//        //Moneda moneda = new Moneda();
//        Moneda moneda = monedaService.obterMonedas();
//        return ResponseEntity.ok(moneda);
//    }

    @GetMapping
    public MindicadorApi getMonedaUf(){
        return monedaService.testeDeUf();
    }

    @GetMapping(value = "/utm")
    public MindicadorApi getMonedaUtm(){
        return monedaService.testeDeUtm();
    }

    @GetMapping(value = "/dolar")
    public ResponseEntity<Dolar> getMonedaDolar(){
        return monedaService.testeDeDolar();
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
